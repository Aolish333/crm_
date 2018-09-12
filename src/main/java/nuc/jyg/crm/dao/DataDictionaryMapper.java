package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface DataDictionaryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);

    ArrayList<DataDictionary> selectAll();

    ArrayList<DataDictionary> selectByKindAndEntryAndValue(@Param("kind") String kind, @Param("entry") String entry,
                                                           @Param("value") String value);
}