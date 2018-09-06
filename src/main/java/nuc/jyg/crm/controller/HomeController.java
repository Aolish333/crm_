package nuc.jyg.crm.controller;

import nuc.jyg.crm.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ji YongGuang.
 * @date 10:52 2018/9/4.
 */
@Controller
public class HomeController {

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping(value = {"", "/"})
    public String welcome() {
        return "index";
    }

    @GetMapping(value = "/sales")
    public String sale() {
        return "sales-opportunity";
    }
}
