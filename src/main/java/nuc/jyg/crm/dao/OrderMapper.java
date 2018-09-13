package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    ArrayList <Order> selectByCustomerIdAndStatus(@Param("id") Integer id, @Param("status") Integer status);

    ArrayList <Order> selectOrdersByUserIdAndStatusAndYears(@Param("id") Integer id, @Param("status") Integer status, @Param("yearsStart") String yearsStart, @Param("yearsEnd") String yearsEnd);

    Order selectLastOrdersByCustomerId(@Param("customerId") Integer customerId);
}