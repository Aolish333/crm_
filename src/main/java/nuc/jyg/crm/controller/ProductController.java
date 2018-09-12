package nuc.jyg.crm.controller;

import lombok.extern.log4j.Log4j2;
import nuc.jyg.crm.model.Product;
import nuc.jyg.crm.service.hikari.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 17:05 2018/9/11.
 * 基础数据
 */
@Log4j2
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping(value = "/details")
    public String getProducts(Model model) {
        ArrayList<Product> products = iProductService.getAll();
        model.addAttribute("products", products);
        return "system-product";
    }

    @GetMapping(value = "/detail/search")
    public String getProduct(String productName, String productModel, String levelBatch, Model model) {
        ArrayList<Product> products = iProductService.get(productName.trim(), productModel.trim(), levelBatch.trim());
        model.addAttribute("products", products);
        return "system-product";
    }

    @GetMapping(value = "/inventorys")
    public String getProductInventory(Model model) {
        ArrayList<Product> products = iProductService.getAll();
        model.addAttribute("products", products);
        return "system-search";
    }

    @GetMapping(value = "/inventory/search")
    public String getProductInventorys(String productName, String warehouse, Model model) {
        ArrayList<Product> products = iProductService.getInventory(productName, warehouse);
        model.addAttribute("products", products);
        return "system-search";
    }

}
