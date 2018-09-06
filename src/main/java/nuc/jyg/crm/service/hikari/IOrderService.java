package nuc.jyg.crm.service.hikari;

import nuc.jyg.crm.model.Order;
import nuc.jyg.crm.model.OrderItem;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ji YongGuang.
 * @date 20:40 2018/8/16.
 */
@SuppressWarnings("JavaDoc")
public interface IOrderService {

    /**
     * 根据用户的id找他所有付过款的订单Order
     *
     * @param id     userId
     * @param status 已付款
     * @return
     */
    ArrayList<Order> selectOrdersByUserId(Integer id, Integer status) throws SQLException;

    /**
     * 根据年份查找已付款的订单
     *
     * @param id
     * @param status 已付款
     * @param year   年份
     * @return
     * @throws SQLException
     */
    ArrayList<Order> selectOrdersByUserIdAndYear(Integer id, Integer status, String year) throws SQLException;

    /**
     * 根据订单号orderNo找到该订单的所有子订单
     *
     * @param orderNo 订单号
     * @return
     * @throws SQLException
     */
    ArrayList<OrderItem> selectOrderItemsByOrderNo(Long orderNo) throws SQLException;

    /**
     * 根据子订单集合计算该订单的总价
     *
     * @param orderItemList 子订单集合
     * @return
     */
    BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList);

    /**
     * 按客户id查找其最后一笔订单
     *
     * @param customerId
     * @return
     */
    Order selectLastOrdersByCustomerId(int customerId) throws SQLException;
}
