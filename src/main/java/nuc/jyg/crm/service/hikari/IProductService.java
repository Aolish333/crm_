package nuc.jyg.crm.service.hikari;

import nuc.jyg.crm.model.Product;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 8:27 2018/9/12.
 */
public interface IProductService {

    ArrayList<Product> getAll();

    ArrayList<Product> get(String productName, String productModel, String levelBatch);

    ArrayList<Product> getInventory(String productName, String warehouse);
}
