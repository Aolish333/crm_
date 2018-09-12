package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.SaleOpportunity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleOpportunityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SaleOpportunity record);

    int insertSelective(SaleOpportunity record);

    SaleOpportunity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleOpportunity record);

    int updateByPrimaryKey(SaleOpportunity record);

    List <SaleOpportunity> selectByStatus(Byte status);

    List <SaleOpportunity> selectByStatusF(Byte status);

    SaleOpportunity selectByNumber(Integer id);

}