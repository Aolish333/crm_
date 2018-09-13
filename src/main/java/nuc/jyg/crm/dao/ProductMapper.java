package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ProductMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    ArrayList<Product> selectAll();

    ArrayList<Product> selectByNameAndModelAndLevel(@Param("productName") String productName, @Param("productModel")
            String productModel, @Param("levelBatch") String levelBatch);

    ArrayList<Product> selectByNameAndWarehouse(@Param("productName") String productName, @Param("warehouse") String
            warehouse);
}