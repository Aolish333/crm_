package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}