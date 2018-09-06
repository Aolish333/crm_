package nuc.jyg.crm.service.hikari.impl;

import nuc.jyg.crm.dao.OrderItemMapper;
import nuc.jyg.crm.dao.OrderMapper;
import nuc.jyg.crm.model.Order;
import nuc.jyg.crm.model.OrderItem;
import nuc.jyg.crm.service.hikari.IOrderService;
import nuc.jyg.crm.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ji YongGuang.
 * @date 20:41 2018/8/16.
 */
@Service(value = "iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public ArrayList<Order> selectOrdersByUserId(Integer id, Integer status) throws SQLException {
        return orderMapper.selectByCustomerIdAndStatus(id, status);
    }

    @Override
    public ArrayList<Order> selectOrdersByUserIdAndYear(Integer id, Integer status, String year) throws SQLException {
        String yearsStart = year + "-01-01";
        String yearsEnd = year + "-12-31";
        return orderMapper.selectOrdersByUserIdAndStatusAndYears(id, status, yearsStart, yearsEnd);
    }

    @Override
    public ArrayList<OrderItem> selectOrderItemsByOrderNo(Long orderNo) throws SQLException {
        return orderItemMapper.selectOrderItemsByOrderNo(orderNo);
    }

    @Override
    public BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }

    @Override
    public Order selectLastOrdersByCustomerId(int customerId) throws SQLException {
        return orderMapper.selectLastOrdersByCustomerId(customerId);
    }
}
