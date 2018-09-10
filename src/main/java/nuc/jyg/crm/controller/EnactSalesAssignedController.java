package nuc.jyg.crm.controller;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 8:18
 * User:Lee
 */

import nuc.jyg.crm.dao.PlanMapper;
import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.Plan;
import nuc.jyg.crm.model.SaleOpportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 指定开发计划
 */
@Controller
public class EnactSalesAssignedController {

    @Autowired
    SaleOpportunityMapper saleOpportunityMaper;

    @Autowired
    PlanMapper planMapper;
    /**
     * 客户开发主页面
     */
    public static final String CUSTOMER_DEVELOP = "customer-develop";

    /** 开发计划页面*/
    public static final String CUSTOMER_DEVELOP_CREATEPLAN = "customer-develop-createplan";
    /** 执行计划页面*/
    public static final String CUSTOMER_DEVELOP_EXECUTEPLAN = "customer-develop-executeplan";

    /** 跳到—》开发计划*/
    @GetMapping(value = "/toAppointPlan/{number}")
    public String toAppointPlan(Model model,@PathVariable String number) {
        SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByNumber(Integer.valueOf(number));
        model.addAttribute("allPlans",returnList(Integer.parseInt(number)));
        model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }

    /** 跳到—》执行计划页面*/
        @GetMapping(value = "/toExecutePlan/{number}" )
    public String toExecutePlan(Model model ,@PathVariable String number) {
            SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByNumber(Integer.valueOf(number));
            model.addAttribute("allPlans",returnList(Integer.parseInt(number)));
            model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_EXECUTEPLAN;
    }

    /** 添加sid的计划*/
    public List<Plan> returnList(int sid){
        List <Plan> plans = planMapper.selectBySid(sid);
        return plans;
    }

}
