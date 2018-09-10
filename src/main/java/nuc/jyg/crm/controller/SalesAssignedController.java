package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.model.SaleOpportunity;
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
    public String create(SaleOpportunity saleOpportunityPara,Model model){

        /**  必填的信息*/
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setFounder(saleOpportunityPara.getFounder());
        saleOpportunity.setCustomerName(saleOpportunityPara.getCustomerName());
        saleOpportunity.setSummary(saleOpportunityPara.getSummary());
        saleOpportunity.setOpportunityDescription(saleOpportunityPara.getOpportunityDescription());
        saleOpportunity.setChance(Byte.valueOf(saleOpportunityPara.getChance()));

        /**  选填信息*/

        saleOpportunity.setNumber(Integer.valueOf(NumberGenerationUtil.number(9)));
        saleOpportunity.setStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));
        saleOpportunity.setContactPhone(saleOpportunityPara.getContactPhone());
        saleOpportunity.setContact(saleOpportunityPara.getContact());

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
    @PostMapping(value = "/dispatch/{id}/{owner}/{assign_time}")
    public String dispatch(@PathVariable (value = "id") Integer id ,
                                     @PathVariable(value = "owner") String owner,
                                     @PathVariable (value = "assign_time") String assign_time,
                           Model model ) {
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setOwner(owner);
        saleOpportunity.setId(id);
        saleOpportunity.setAssignTime(DateTimeUtil.strToDates(assign_time));

        saleOpportunity.setStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.ALLOCATED.getCode()));

        salesAssignedService.alterSalesAssigned(saleOpportunity);

        /** 返回销售机会管理*/
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));

        model.addAttribute("allSales", opportunityList);

        return SALES_OPPORTUNITY;
    }

    @PostMapping(value = "/edit")
    public String edit(SaleOpportunity saleOpportunityPara ,Model model) {
        SaleOpportunity saleOpportunity = new SaleOpportunity();

        saleOpportunity.setFounder(saleOpportunityPara.getFounder());
        saleOpportunity.setCustomerName(saleOpportunityPara.getCustomerName());
        saleOpportunity.setSummary(saleOpportunityPara.getSummary());
        saleOpportunity.setOpportunityDescription(saleOpportunityPara.getOpportunityDescription());
        saleOpportunity.setChance(Byte.valueOf(saleOpportunityPara.getChance()));


        saleOpportunity.setContact(saleOpportunityPara.getContact());
        saleOpportunity.setContactPhone(saleOpportunity.getContactPhone());
        saleOpportunity.setSource(saleOpportunityPara.getSource());

        saleOpportunity.setId(saleOpportunityPara.getId());

        salesAssignedService.alterSalesAssigned(saleOpportunity);

        /** 返回销售机会管理*/
        List<SaleOpportunity> opportunityList =  salesAssignedService.querySaleOpportunityByStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));

        model.addAttribute("allSales", opportunityList);
        return SALES_OPPORTUNITY;
    }

}
