package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.dao.CustomerMapper;
import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * @author Ji YongGuang.
 * @date 10:52 2018/9/4.
 */
@Log4j2
@Controller
public class HomeController {

    /**
     * 客户开发主页面
     */
    public static final String CUSTOMER_DEVELOP = "customer-develop";
    public static final String SALES_OPPORTUNITY = "sales-opportunity";
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    SalesAssignedService salesAssignedService;

    @Autowired
    SaleOpportunityMapper saleOpportunityMapper;

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
    @GetMapping(value = "/sales")
    public String sale(Model model) {
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        model.addAttribute("allSales", opportunityList);
        return SALES_OPPORTUNITY;
    }

    /**
     * @return 客户开发计划
     */
    @GetMapping(value = "/developingCustomer")
    public String developingCustomer(Model model) {
        List<SaleOpportunity> opportunityList = saleOpportunityMapper.selectByStatusF(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        model.addAttribute("allSales", opportunityList);
        return CUSTOMER_DEVELOP;
    }

    /**
     * @return 创建服务
     */
    @GetMapping(value = "/createService")
    public String createService() {
        return "service-create";
    }

    @GetMapping(value = "/serviceDistribution")
    public String serviceDistribution() {
        return "service-distribution";
    }

    @GetMapping(value = "/serviceProcess")
    public String serviceProcess() {
        return "service-handle";
    }


    @GetMapping(value = "/serviceFeedback")
    public String serviceFeedback() {
        return "service-feedback";
    }

    @GetMapping(value = "/serviceArchive")
    public String archiveServices() {
        return "service-file";
    }

}
