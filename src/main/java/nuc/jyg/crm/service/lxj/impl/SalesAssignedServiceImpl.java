package nuc.jyg.crm.service.lxj.impl;

import nuc.jyg.crm.common.ResponseCodeEnum;
import nuc.jyg.crm.dao.SaleOpportunityMapper;
import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/5 10:22
 * User:Lee
 */
@Service("LSalesAssignedService")
public class SalesAssignedServiceImpl implements SalesAssignedService {

    @Autowired
    private SaleOpportunityMapper saleOpportunityMapper;

    @Override
    public ResponseCodeEnum createSaleOpportunity(SaleOpportunity saleOpportunity) {
        if (saleOpportunity == null){
            return ResponseCodeEnum.ERROR;
        }
        saleOpportunityMapper.insert(saleOpportunity);
        return ResponseCodeEnum.SUCCESS;
    }

    @Override
    public List<SaleOpportunity> querySaleOpportunityByStatus(Byte by_tatus) {
        return null;
    }

    @Override
    public ResponseCodeEnum AlterSalesAssigned() {
        return null;
    }
}
