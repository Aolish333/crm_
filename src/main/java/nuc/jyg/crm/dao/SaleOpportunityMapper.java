package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.SaleOpportunity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    List <SaleOpportunity> selectByTime(@Param("customerName")String customerName, @Param("startTime")Date startTime, @Param("endTime") Date endTime, @Param("status1") Byte status1, @Param("status2") Byte status2);

    SaleOpportunity selectByNumber(Integer id);

}