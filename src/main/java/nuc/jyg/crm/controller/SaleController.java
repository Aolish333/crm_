package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.dao.EmployeeMapper;
import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.Employee;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.model.TimeQueryL;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/6 16:36
 * User:Lee
 */

/**
 * 以销售管理的get方法为主
 */
@Controller
public class SaleController {
    public static final String SALES_OPPORTUNITY_EDIT = "sales-opportunity-edit";
    public static final String SALES_OPPORTUNITY = "sales-opportunity";
    public static final String SALES_OPPORTUNITY_DISPATCH = "sales-opportunity-dispatch";
    @Autowired
    SaleOpportunityMapper saleOpportunityMaper;

    @Autowired
    EmployeeMapper employeeMapper;

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

        /** 查询可以分配的销售经理*/
        List<Employee> employees = employeeMapper.selectAllByRole(Const.SystemUserEnum.CUSTOMER_MANAGER.getCode());

        List <String> strings = new ArrayList();
        for (Employee employee : employees) {
            strings.add(employee.getName());
        }
        model.addAttribute("allEmployee", strings);

        return SALES_OPPORTUNITY_DISPATCH;
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        SaleOpportunity saleOpportunity = saleOpportunityMaper.selectByPrimaryKey(id);
        model.addAttribute("allSale", saleOpportunity);
        return SALES_OPPORTUNITY_EDIT;
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        /** 返回销售机会管理*/
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setId(id);
        salesAssignedService.dropSalesAssigned(saleOpportunity);
        List <SaleOpportunity> opportunityList = salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        model.addAttribute("allSales", opportunityList);
        return SALES_OPPORTUNITY;
    }

    /** 根据日期进行查询*/
    @PostMapping(value = "querySaleByTime")
    public String query(TimeQueryL timeQueryL,Model model){

        return SALES_OPPORTUNITY;
    }

}
