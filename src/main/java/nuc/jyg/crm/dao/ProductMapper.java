package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}