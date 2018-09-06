package nuc.jyg.crm.service.hikari.impl;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.dao.CustomerLossMapper;
import nuc.jyg.crm.dao.CustomerMapper;
import nuc.jyg.crm.dao.OrderMapper;
import nuc.jyg.crm.model.Customer;
import nuc.jyg.crm.model.Order;
import nuc.jyg.crm.model.OrderItem;
import nuc.jyg.crm.service.hikari.IOrderService;
import nuc.jyg.crm.service.hikari.IReportService;
import nuc.jyg.crm.util.BigDecimalUtil;
import nuc.jyg.crm.vo.CustomerVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ji YongGuang.
 * @date 17:06 2018/8/16.
 */
@SuppressWarnings("JavaDoc")
@Service(value = "iReportService")
public class ReportServiceImpl implements IReportService {

    private static final String ALL_YEARS = "全部";

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerLossMapper customerLossMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 可以根据客户名称或年份查询，默认列出全部客户和所有年份订单金额的总和
     * <p>
     * 目的:显示客户名称和该客户下单的总金额。
     * </p>
     *
     * @param customerName 客户名称
     * @param years        年份
     * @return 显示客户名称和该客户下单的总金额
     */
    @Override
    public List<CustomerVO> getCustomerContribution(String customerName, String years) throws SQLException {
        ArrayList<Customer> customerArrayList = null;

        customerArrayList = customerMapper.selectByName(customerName);
        if (StringUtils.isBlank(customerName)) {// 默认查询
            customerArrayList = customerMapper.selectAllCustomers();
        }

        ArrayList<Order> orderArrayList = null;
        Map<Customer, ArrayList<Order>> map = new HashMap<>();

        // K -用户 V -订单列表
        if (CollectionUtils.isNotEmpty(customerArrayList)) {
            for (Customer targetCustomer : customerArrayList) {
                if (StringUtils.equals(ALL_YEARS, years)) {
                    orderArrayList = orderMapper.selectByCustomerIdAndStatus(targetCustomer.getId(), Const
                            .PaymentStatusEnum
                            .ALREADY_PAID);
                    map.put(targetCustomer, orderArrayList);
                } else {
                    String yearsStart = years + "-01-01";
                    String yearsEnd = years + "-12-31";
                    orderArrayList = orderMapper.selectOrdersByUserIdAndStatusAndYears(targetCustomer.getId(), Const
                            .PaymentStatusEnum.ALREADY_PAID, yearsStart, yearsEnd);
                    map.put(targetCustomer, orderArrayList);
                }
            }
        }

        List<CustomerVO> customerVOList = new ArrayList<CustomerVO>(10);

        Iterator iterator = (Iterator) map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            // 每个客户的订单列表
            ArrayList<Order> orderList = (ArrayList<Order>) m.getValue();
            // 查找客户的姓名
            Customer customer = (Customer) m.getKey();

            BigDecimal totalPrice = new BigDecimal("0");
            for (Order targetOrder : orderList
                    ) {
                // 计算该Order总金额
                ArrayList<OrderItem> orderItems = iOrderService.selectOrderItemsByOrderNo(targetOrder.getOrderNo());
                BigDecimal currentOrderPrice = iOrderService.getOrderTotalPrice(orderItems);
                totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(), currentOrderPrice.doubleValue());
            }

            // CustomerVo包装
            CustomerVO customerVO = assembleCustomerVO(customer);
            customerVO.setOrderAmount(totalPrice);
            customerVOList.add(customerVO);
        }

        return customerVOList;
    }

    /**
     * 可以选择报表方式，按客户等级统计、按信用度统计或按满意度统计。
     * <p>
     * 目的:列出统计项，和该统计项下有多少个客户。
     * </p>
     *
     * @param manner 报表方式
     * @return
     */
    @Override
    public Map<String, Integer> getCustomerConstract(String manner) throws SQLException {
        Map<String, Integer> map = new HashMap<String, Integer>(5, 0.75f);
        if (StringUtils.equals(manner, Const.ClassificationQueryEnum.CUSTOMER_LEVEL.getValue())) {// 按等级
            Const.CustomerGradeEnum[] customerGradeEnums = Const.CustomerGradeEnum.values();
            for (Const.CustomerGradeEnum targetCustomerGradeEnum :
                    customerGradeEnums) {
                // [ "战略合作伙伴":3,"合作伙伴":2 ..]
                map.put(targetCustomerGradeEnum.getValue(), customerMapper.selectQuantityOfCustomerByGrade
                        (targetCustomerGradeEnum.getCode()));
            }
        }
        if (StringUtils.equals(manner, Const.ClassificationQueryEnum.CREDIT.getValue())) {// 按信用度
            Const.CustomerCreditEnum[] customerCreditEnums = Const.CustomerCreditEnum.values();
            for (Const.CustomerCreditEnum targetCustomerCreditEnum : customerCreditEnums
                    ) {
                map.put(targetCustomerCreditEnum.getValue(), customerMapper.selectQuantityOfCustomerByCredit
                        (targetCustomerCreditEnum.getCode()));
            }
        }
        if (StringUtils.equals(manner, Const.ClassificationQueryEnum.SATISFACTION.getValue())) {// 按满意度
            Const.CustomerSatisfactionEnum[] customerSatisfactionEnums = Const.CustomerSatisfactionEnum.values();
            for (Const.CustomerSatisfactionEnum targetCustomerSatisfactionEnum : customerSatisfactionEnums
                    ) {
                map.put(targetCustomerSatisfactionEnum.getValue(), customerMapper
                        .selectQuantityOfCustomerBySatisfaction(
                                targetCustomerSatisfactionEnum.getCode()));
            }
        }

        return map;
    }

    /**
     * 查看已经确认流失的客户流失记录
     * <p>
     * 目的:列出符合查询条件的已经确认流失的客户流失记录
     * </p>
     *
     * @param username 客户名称
     * @param manager  客户经理
     * @return
     */
    @Override
    public Map<String, CustomerVO> getCustomerLoss(String username, String manager) throws SQLException {
        Map<String, CustomerVO> map = new HashMap<String, CustomerVO>(10, 0.75f);
        ArrayList<Customer> customerArrayList = null;

        final boolean doubleNull = StringUtils.isBlank(username) && StringUtils.isBlank(manager);
        final boolean usernameNotNull = StringUtils.isNotBlank(username) && StringUtils.isBlank(manager);
        final boolean managerNotNull = StringUtils.isNotBlank(manager) && StringUtils.isBlank(username);
        final boolean doubleNotNull = StringUtils.isNotBlank(username) && StringUtils.isNotBlank(manager);

        // username = null & manager != null
        if (managerNotNull) {
            customerArrayList = customerLossMapper.selectCustomerByManagerName(manager);
        }

        // 两者都不为null
        if (doubleNotNull) {
            customerArrayList = customerLossMapper.selectCusByCusNameAndManName(username, manager);
        }

        // username != null & manager = null 和 两者都为 null
        if (doubleNull || usernameNotNull) {
            customerArrayList = customerLossMapper.selectCusAndManByName(username);
        }

        // 已确认流失的客户集合
        for (Customer targetCustomer :
                customerArrayList) {
            CustomerVO customerVO = assembleCustomerVO(targetCustomer);
            // TODO Years 客户最后一次下单记录
            Order lastOrder = iOrderService.selectLastOrdersByCustomerId(targetCustomer.getId());
            if (lastOrder.getId() != 0) {
                String lastOrderTime = lastOrder.getUpdateTime().toString();
                lastOrderTime = lastOrderTime.substring(lastOrderTime.length() - 4, lastOrderTime.length());
                map.put(lastOrderTime, customerVO);
            }
        }

        return map;
    }

    private CustomerVO assembleCustomerVO(Customer customer) throws SQLException {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setId(customer.getId());
        customerVO.setUsernmae(customer.getCustomerName());
        customerVO.setManagerName(customer.getManager());

        Const.CauseOfLossEnum[] causeOfLossEnums = Const.CauseOfLossEnum.values();
        Random random = new Random();
        final int enumLength = causeOfLossEnums.length;
        customerVO.setLossReason(causeOfLossEnums[random.nextInt(enumLength)].getValue());

        return customerVO;
    }

}
