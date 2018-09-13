package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Mapper
public interface CustomerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    ArrayList<Customer> selectByName(@Param("customerName") String customerName);

    ArrayList<Customer> selectAllCustomers();

    List<Customer> queryList();

    int selectQuantityOfCustomerByGrade(@Param("grade") Integer grade);

    int selectQuantityOfCustomerByCredit(@Param("credit") Integer credit);

    int selectQuantityOfCustomerBySatisfaction(@Param("satisfaction") Integer satisfaction);

    ArrayList<Customer> selectByLeval(@Param("leval") Integer leval);

}