package nuc.jyg.crm.service.lxj;

import nuc.jyg.crm.common.ResponseCodeEnum;
import nuc.jyg.crm.model.SaleOpportunity;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 8:36
 * User:Lee
 */
public interface DevolopSalesService {

    ResponseCodeEnum enactSalesAssigned(SaleOpportunity saleOpportunity);

    ResponseCodeEnum executeSalesAssigned(SaleOpportunity saleOpportunity);

    ResponseCodeEnum devolopSalesService(SaleOpportunity saleOpportunity);
}
