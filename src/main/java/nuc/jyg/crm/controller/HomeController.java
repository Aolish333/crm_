package nuc.jyg.crm.controller;

import lombok.extern.log4j.Log4j2;
import nuc.jyg.crm.dao.CustomerMapper;
import nuc.jyg.crm.model.Employee;
import nuc.jyg.crm.service.hikari.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Ji YongGuang.
 * @date 10:52 2018/9/4.
 */
@Log4j2
@Controller
public class HomeController {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private IEmployeeService iEmployeeService;

    @GetMapping(value = {"", "/"})
    public String welcome() {
        return "login";
    }

    @PostMapping(value = {"/login"})
    public String login(String username, String password, HttpSession httpSession) {
        Employee currentEmployee = iEmployeeService.getByNameAndPassword(username, password);
        log.info("姓名:{} - role :{}", currentEmployee.getName(), currentEmployee.getRole());
        httpSession.setAttribute("currentEmployee", currentEmployee);
        return "index";
    }

    @GetMapping(value = {"/logout", "/check"})
    public String logout(HttpSession httpSession) {
        log.info("用户退出 | 切换用户");
        httpSession.invalidate();
        return "login";
    }

    @GetMapping(value = {"/welcome"})
    public String welcomes() {
        return "welcome";
    }
}
