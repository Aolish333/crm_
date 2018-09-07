package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/6 16:36
 * User:Lee
 */

/**
 * 以销售管理的post方法为主
 */
@Controller
//@RequestMapping(value = "/sale")
public class SaleController {
    @Autowired
    SaleOpportunityMapper saleOpportunityMaper;

    @Autowired
    SalesAssignedService salesAssignedService;

    @GetMapping(value = "/addOpportunity")
    public String addOpportunity() {
        return "sales-opportunity-add";
    }

    @GetMapping(value = "/assign/{id}")
    public String assign(@PathVariable Integer id, Model model) {
        SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByPrimaryKey(id);
        model.addAttribute("allSale", saleOpportunity);
        return "sales-opportunity-dispatch";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByPrimaryKey(id);
        model.addAttribute("allSale", saleOpportunity);
        return "sales-opportunity-edit.html";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        /** 返回销售机会管理*/
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setId(id);
        salesAssignedService.dropSalesAssigned(saleOpportunity);
        List <SaleOpportunity> opportunityList = salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        model.addAttribute("allSales", opportunityList);
        return "sales-opportunity";
    }

}
