package nuc.jyg.crm.service.hikari.impl;

import nuc.jyg.crm.dao.ProductMapper;
import nuc.jyg.crm.model.Product;
import nuc.jyg.crm.service.hikari.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 8:27 2018/9/12.
 */
@Service(value = "iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ArrayList<Product> getAll() {
        return productMapper.selectAll();
    }

    @Override
    public ArrayList<Product> get(String productName, String productModel, String levelBatch) {
        if (StringUtils.equals(productName, "")) {
            productName = null;
        }
        if (StringUtils.equals(productModel, "")) {
            productModel = null;
        }
        if (StringUtils.equals(levelBatch, "")) {
            levelBatch = null;
        }
        return productMapper.selectByNameAndModelAndLevel(productName, productModel, levelBatch);
    }

    @Override
    public ArrayList<Product> getInventory(String productName, String warehouse) {
        if (StringUtils.equals(productName, "")) {
            productName = null;
        }
        if (StringUtils.equals(warehouse, "")) {
            warehouse = null;
        }
        return productMapper.selectByNameAndWarehouse(productName, warehouse);
    }
}
