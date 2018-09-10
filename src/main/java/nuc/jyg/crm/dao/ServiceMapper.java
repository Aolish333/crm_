package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Service;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServiceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    int selectCountByYear(@Param("year") String year, @Param("way") String way);

}