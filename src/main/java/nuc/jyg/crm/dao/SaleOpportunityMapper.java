package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.SaleOpportunity;

public interface SaleOpportunityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SaleOpportunity record);

    int insertSelective(SaleOpportunity record);

    SaleOpportunity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleOpportunity record);

    int updateByPrimaryKey(SaleOpportunity record);
}