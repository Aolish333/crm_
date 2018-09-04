package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Service;

public interface ServiceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
}