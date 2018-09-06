package nuc.jyg.crm.service.lxj;

import nuc.jyg.crm.common.ResponseCodeEnum;
import nuc.jyg.crm.model.SaleOpportunity;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 9:59
 * User:Lee
 */

public interface SalesAssignedService {
    ResponseCodeEnum createSaleOpportunity(SaleOpportunity saleOpportunity);

    List <SaleOpportunity> querySaleOpportunityByStatus(Byte by_tatus);

    ResponseCodeEnum AlterSalesAssigned();

}
