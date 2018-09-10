package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.CustomerLoss;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CustomerLossMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLoss record);

    int insertSelective(CustomerLoss record);

    CustomerLoss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLoss record);

    int updateByPrimaryKey(CustomerLoss record);

    ArrayList<CustomerLoss> selectCusByCusNameAndManName(@Param("customerName") String customerName,
                                                         @Param("managerName") String managerName,
                                                         @Param("status") Integer status);
}