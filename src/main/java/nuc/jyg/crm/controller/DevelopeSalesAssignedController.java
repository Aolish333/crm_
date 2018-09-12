package nuc.jyg.crm.controller;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 8:20
 * User:Lee
 */

import nuc.jyg.crm.common.Const;
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

import java.util.List;

/**
 * 开发计划（成功/失败）
 */
@Controller
public class DevelopeSalesAssignedController {


    /** 执行页面*/
    public static final String CUSTOMER_DEVELOP_EXECUTEPLAN = "customer-develop-executeplan";
    @Autowired
    PlanMapper planMapper;

    @Autowired
    SaleOpportunityMapper saleOpportunityMapper;


    /**
     * 客户开发主页面
     */
    public static final String CUSTOMER_DEVELOP = "customer-develop";
    /** 制定计划*/
    @PostMapping(value = "/enactPlan" )
    public String enactPlan(Model model, Plan plan) {
        planMapper.insertSelective(plan);
//        错误
        return CUSTOMER_DEVELOP;
    }

    /** -》开发成功 */
    @GetMapping(value = "/toDevelopSuccess/{id}" )
    public String toDevelopSuccess(Model model,@PathVariable Integer id){
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setId(id);
        saleOpportunity.setStatus((byte) Const.SaleOpportunityStatusEnum.DEVELOPMENT_SUCCESSFUL.getCode());
        saleOpportunityMapper.updateByPrimaryKeySelective(saleOpportunity);
        SaleOpportunity saleOpportunityOK = saleOpportunityMapper.selectByPrimaryKey(id);
        model.addAttribute("allPlans", returnList(saleOpportunityOK.getNumber()));
        model.addAttribute("allSale", saleOpportunityOK);
        return CUSTOMER_DEVELOP_EXECUTEPLAN;
    }

    /** -》开发失败 */
    @GetMapping(value = "/toDevelopFalse/{id}" )
    public String toDevelopFalse(Model model,@PathVariable Integer id){
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setId(id);
        saleOpportunity.setStatus((byte) Const.SaleOpportunityStatusEnum.DEVELOPMENT_FAILURE.getCode());
        saleOpportunityMapper.updateByPrimaryKeySelective(saleOpportunity);
        SaleOpportunity saleOpportunityOK = saleOpportunityMapper.selectByPrimaryKey(id);
        model.addAttribute("allPlans", returnList(saleOpportunityOK.getNumber()));
        model.addAttribute("allSale", saleOpportunityOK);
        return CUSTOMER_DEVELOP_EXECUTEPLAN;
    }

    /** 添加sid的计划*/
    public List<Plan> returnList(int sid){
        List <Plan> plans = planMapper.selectBySid(sid);
        return plans;
    }

}
