package nuc.jyg.crm.controller;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 8:18
 * User:Lee
 */

import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.SaleOpportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 指定开发计划
 */
@Controller
public class EnactSalesAssignedController {

    @Autowired
    SaleOpportunityMapper saleOpportunityMaper;

    /** 开发计划页面*/
    public static final String CUSTOMER_DEVELOP_CREATEPLAN = "customer-develop-createplan";
    /** 执行计划页面*/
    public static final String CUSTOMER_DEVELOP_EXECUTEPLAN = "customer-develop-executeplan";

    @GetMapping(value = "/toAppointPlan/{id}")
    public String toAppointPlan(Model model,@PathVariable Integer id) {
        SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByPrimaryKey(id);
        model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }

    /** 执行计划页面*/
        @GetMapping(value = "/toExecutePlan/{id}" )
    public String toExecutePlan(Model model,@PathVariable Integer id) {
            SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByPrimaryKey(id);
            model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_EXECUTEPLAN;
    }
}
