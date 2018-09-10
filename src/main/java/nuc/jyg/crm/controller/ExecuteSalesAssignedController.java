package nuc.jyg.crm.controller;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 8:19
 * User:Lee
 */

import nuc.jyg.crm.dao.PlanMapper;
import nuc.jyg.crm.model.Plan;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 执行开发计划
 */
@Controller
public class ExecuteSalesAssignedController {

    @Autowired
    PlanMapper planMapper;

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

    /** 根据销售ID新建计划 */
    @GetMapping(value = "/createPlanBySaleId/{sid}/{createTime}/{planContent}")
    public String createPlanBySaleId(Model model,
                                     @PathVariable Integer sid,
                                     @PathVariable String createTime,
                                     @PathVariable String planContent){
        Plan plan = new Plan();
        plan.setSaleId(sid);
        plan.setCreateTime(DateTimeUtil.strToDates(createTime));
        plan.setPlanContent(planContent);
        planMapper.insert(plan);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }

    /** 根据计划ID修改计划项 */
    @GetMapping(value = "/updataPlan/{id}/{planContent}")
    public String updataPlan(Model model,
                             @PathVariable Integer id,
                             @PathVariable String planContent){
        Plan plan = new Plan();
        plan.setPlanContent(planContent);
        plan.setSaleId(id);
        planMapper.updateByPrimaryKey(plan);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }


    /** 根据计划ID删除计划项 */
    @GetMapping(value = "/deletePlan/{id}")
    public String deletePlan(Model model,
                             @PathVariable Integer id){
        planMapper.deleteByPrimaryKey(id);
        return CUSTOMER_DEVELOP_CREATEPLAN;
    }

//    /** 返回客户页面 */
//    @PostMapping(value = "/to")
//    public String deletePlan(Model model){
//        return "CUSTOMER_DEVELOP";
//    }



}
