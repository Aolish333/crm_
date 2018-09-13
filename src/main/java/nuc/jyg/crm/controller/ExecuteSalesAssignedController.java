package nuc.jyg.crm.controller;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 8:19
 * User:Lee
 */

import nuc.jyg.crm.dao.PlanMapper;
import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.Plan;
import nuc.jyg.crm.model.PlanTime;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 执行开发计划
 */
@Controller
public class ExecuteSalesAssignedController {

    @Autowired
    PlanMapper planMapper;

    @Autowired
    SaleOpportunityMapper saleOpportunityMapper;

    /** 执行页面*/
    public static final String CUSTOMER_DEVELOP_EXECUTEPLAN = "customer-develop-executeplan";

    /**
     * 客户开发主页面
     */
    public static final String CUSTOMER_DEVELOP = "customer-develop";
    /**
     * 指定计划
     */
    public static final String CUSTOMER_DEVELOP_CREATEPLAN = "customer-develop-createplan";

    /**
     * 执行计划
     * 获取前台销售编号，更改所以SID销售编号的计划
     * */
    @PostMapping(value = "/executePlan" )
    public String executePlan(Model model, SaleOpportunity saleOpportunity) {

        return CUSTOMER_DEVELOP;
    }


    /** 根据计划ID添加执行效果 */
    @PostMapping(value = "/updataPlanResult" )
    public String updataPlanResult(Model model,Plan plan){
        planMapper.updateByPrimaryKeySelective(plan);
        SaleOpportunity saleOpportunity = saleOpportunityMapper.selectByNumber(plan.getSaleId());
        model.addAttribute("allPlans",returnList(plan.getSaleId()));
        model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_EXECUTEPLAN;
    }

    /** 根据销售ID新建计划 */
    @RequestMapping(value = "/createPlanBySaleId", method=RequestMethod.POST)
    public String createPlanBySaleId(Model model,PlanTime planTime) throws Exception{
        Plan plan = new Plan();
        plan.setSaleId(planTime.getSaleId());
        plan.setCreateTime(DateTimeUtil.strToDates(planTime.getCreateTime()));
        plan.setPlanContent(planTime.getPlanContent());
        planMapper.insert(plan);
        SaleOpportunity saleOpportunity = saleOpportunityMapper.selectByNumber(planTime.getSaleId());
        model.addAttribute("allPlans",returnList(plan.getSaleId()));
        model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }

    /** 根据计划ID修改计划项 */
    @PostMapping(value = "/updataPlan")
    public String updataPlan(Model model,@ModelAttribute Plan plan){
        planMapper.updateByPrimaryKeySelective(plan);
        System.out.println("__________"+plan.toString());
        SaleOpportunity saleOpportunity = saleOpportunityMapper.selectByNumber(plan.getSaleId());
        model.addAttribute("allPlans",returnList(plan.getSaleId()));
        model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }


    /** 根据计划ID删除计划项 */
    @GetMapping(value = "/deletePlan/{id}")
    public String deletePlan(Model model,@PathVariable Integer id){
        Plan plan = planMapper.selectByPrimaryKey(id);
        SaleOpportunity saleOpportunity = saleOpportunityMapper.selectByNumber(plan.getSaleId());
        planMapper.deleteByPrimaryKey(id);
        model.addAttribute("allPlans",returnList(plan.getSaleId()));
        model.addAttribute("allSale", saleOpportunity);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }

    /** 添加sid的计划*/
    public List<Plan> returnList(int sid){
        List <Plan> plans = planMapper.selectBySid(sid);
        return plans;
    }

}
