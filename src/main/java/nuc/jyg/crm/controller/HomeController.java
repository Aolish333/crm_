package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.dao.CustomerMapper;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Ji YongGuang.
 * @date 10:52 2018/9/4.
 */
@Controller
public class HomeController {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    SalesAssignedService salesAssignedService;

    @GetMapping(value = {"", "/"})
    public String welcome() {
        return "index";
    }

    @GetMapping(value = "/sales")
    public String sale(Model model) {
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        model.addAttribute("allSales", opportunityList);
        return "sales-opportunity";
    }

    /**
     * @return 客户开发计划
     */
    @GetMapping(value = "/developingCustomer")
    public String developingCustomer(Model model) {
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.ALLOCATED.getCode()));
        model.addAttribute("allSales", opportunityList);
        return "customer-develop";
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
