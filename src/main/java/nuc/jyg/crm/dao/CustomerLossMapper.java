package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.CustomerLoss;

public interface CustomerLossMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLoss record);

    int insertSelective(CustomerLoss record);

    CustomerLoss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLoss record);

    int updateByPrimaryKey(CustomerLoss record);
}