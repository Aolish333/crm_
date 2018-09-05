package nuc.jyg.crm.controller;

import nuc.jyg.crm.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ji YongGuang.
 * @date 10:52 2018/9/4.
 */
@RestController
public class HomeController {

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping(value = {"", "/"})
    public String welcome() {
        return "hikari";
    }
}
