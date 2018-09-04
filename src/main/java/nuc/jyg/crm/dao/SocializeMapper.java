package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Socialize;

public interface SocializeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Socialize record);

    int insertSelective(Socialize record);

    Socialize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Socialize record);

    int updateByPrimaryKey(Socialize record);
}