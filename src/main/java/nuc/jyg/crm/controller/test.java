package nuc.jyg.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 15:38
 * User:Lee
 */
@Controller
public class test {
    @RequestMapping(value = "/test")
    public String test(){
        return "/index";
    }
    @RequestMapping(value = "/test1")
    public String test1(){
        return "/sales-opportunity";
    }
}
