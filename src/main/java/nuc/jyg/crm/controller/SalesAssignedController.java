package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.model.SaleOpportunityTime;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import nuc.jyg.crm.util.DateTimeUtil;
import nuc.jyg.crm.util.NumberGenerationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 9:43
 * User:Lee
 */
@Controller
@RequestMapping(value = "/sale/opportunity")
public class SalesAssignedController {

        public static final String SALES_OPPORTUNITY = "sales-opportunity";
    @Autowired
    private SalesAssignedService salesAssignedService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String reqParameters(@PathVariable String id, Model model){
        return "reqparameter/showInput";
    }

    @PostMapping(value = "/create")
    public String create(SaleOpportunityTime saleOpportunityTime,Model model){

        /**  必填的信息*/
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setFounder(saleOpportunityTime.getFounder());
        saleOpportunity.setCustomerName(saleOpportunityTime.getCustomerName());
        saleOpportunity.setSummary(saleOpportunityTime.getSummary());
        saleOpportunity.setOpportunityDescription(saleOpportunityTime.getOpportunityDescription());
        saleOpportunity.setChance(Byte.valueOf(saleOpportunityTime.getChance()));

        /**  选填信息*/

        saleOpportunity.setSource(saleOpportunityTime.getSource());
        saleOpportunity.setFounder(saleOpportunityTime.getFounder());

        saleOpportunity.setNumber(Integer.valueOf(NumberGenerationUtil.number(9)));
        saleOpportunity.setStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        saleOpportunity.setContactPhone(saleOpportunityTime.getContactPhone());
        saleOpportunity.setContact(saleOpportunityTime.getContact());

        salesAssignedService.createSaleOpportunity(saleOpportunity);

        /** 返回销售机会管理*/
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));

        model.addAttribute("allSales", opportunityList);

        return SALES_OPPORTUNITY;
    }

    /**
     * 编辑post
     * @param id
     * @param owner
     * @param assign_time
     * @return
     */
    @PostMapping(value = "/dispatch")
    public String dispatch(SaleOpportunityTime saleOpportunityTime, Model model ) {
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setOwner(saleOpportunityTime.getOwner());
        saleOpportunity.setId(saleOpportunityTime.getId());
        saleOpportunity.setAssignTime(DateTimeUtil.strToDate(saleOpportunityTime.getAssignTime()));

        saleOpportunity.setStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.ALLOCATED.getCode()));

        salesAssignedService.alterSalesAssigned(saleOpportunity);

        /** 返回销售机会管理*/
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));

        model.addAttribute("allSales", opportunityList);

        return SALES_OPPORTUNITY;
    }

    @PostMapping(value = "/edit")
    public String edit(SaleOpportunityTime saleOpportunityTime , Model model) {
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setId(saleOpportunityTime.getId());
        saleOpportunity.setFounder(saleOpportunityTime.getFounder());
        saleOpportunity.setCustomerName(saleOpportunityTime.getCustomerName());
        saleOpportunity.setSummary(saleOpportunityTime.getSummary());
        saleOpportunity.setOpportunityDescription(saleOpportunityTime.getOpportunityDescription());
        saleOpportunity.setChance(Byte.valueOf(saleOpportunityTime.getChance()));

        saleOpportunity.setCreateTime(DateTimeUtil.strToDate(saleOpportunityTime.getCreateTime()));
        saleOpportunity.setAssignTime(DateTimeUtil.strToDate(saleOpportunityTime.getAssignTime()));
        saleOpportunity.setOwner(saleOpportunityTime.getOwner());

        saleOpportunity.setNumber(saleOpportunityTime.getNumber());
        saleOpportunity.setContactPhone(saleOpportunityTime.getContactPhone());
        saleOpportunity.setContact(saleOpportunityTime.getContact());

        saleOpportunity.setStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.ALLOCATED.getCode()));
        salesAssignedService.alterSalesAssigned(saleOpportunity);

        /** 返回销售机会管理*/
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));

        model.addAttribute("allSales", opportunityList);
        return SALES_OPPORTUNITY;
    }

}
