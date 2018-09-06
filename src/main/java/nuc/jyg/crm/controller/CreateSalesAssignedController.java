package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.common.ResponseCodeEnum;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 9:43
 * User:Lee
 */
@RestController
@RequestMapping(value = "/sale/opportunity")
public class CreateSalesAssignedController {

    @Autowired
    private SalesAssignedService salesAssignedService;

    @PostMapping(value = "/")
    public ResponseCodeEnum create(@RequestParam(value = "customer_name") String customer_name ,@RequestParam(value = "summary") String summary,@RequestParam(value = "opportunity_description") String opportunity_description,@RequestParam(value = "founder") String founder,@RequestParam(value = "chance") String chance){
        /**  必填的信息*/
        SaleOpportunity saleOpportunity = new SaleOpportunity();
        saleOpportunity.setFounder(founder);
        saleOpportunity.setCustomerName(customer_name);
        saleOpportunity.setSummary(summary);
        saleOpportunity.setOpportunityDescription(opportunity_description);
        saleOpportunity.setChance(Byte.valueOf(chance));

        /**  选填信息*/
        saleOpportunity.setNumber(1);
        saleOpportunity.setStatus(Byte.valueOf((byte) Const.SaleOpportunityStatusEnum.UNDISTRIBUTED.getCode()));

        System.out.printf(String.valueOf(saleOpportunity.getStatus()));

        salesAssignedService.createSaleOpportunity(saleOpportunity);

        return ResponseCodeEnum.SUCCESS;
    }
}
