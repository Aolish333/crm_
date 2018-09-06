package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Contact;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Contact record);

    int insertSelective(Contact record);

    Contact selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contact record);

    int updateByPrimaryKey(Contact record);
}