package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.DataDictionary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataDictionaryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
}