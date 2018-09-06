package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Plan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);
}