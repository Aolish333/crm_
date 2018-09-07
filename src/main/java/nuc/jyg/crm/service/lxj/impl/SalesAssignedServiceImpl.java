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

    /**
     * 创建销售机会
     * @param saleOpportunity
     * @return
     */
    @Override
    public ResponseCodeEnum createSaleOpportunity(SaleOpportunity saleOpportunity) {
        if (saleOpportunity == null) {
            return ResponseCodeEnum.ERROR;
        }
        saleOpportunityMapper.insert(saleOpportunity);
        return ResponseCodeEnum.SUCCESS;
    }

    /**
     * 根据销售状态查询销售机会
     * @param by_tatus 分配状态
     * @return
     */
    @Override
    public List <SaleOpportunity> querySaleOpportunityByStatus(Byte by_tatus) {
        List <SaleOpportunity> saleOpportunities = saleOpportunityMapper.selectByStatus(by_tatus);
        return saleOpportunities;
    }

    @Override
    public ResponseCodeEnum alterSalesAssigned(SaleOpportunity saleOpportunity) {
        if (saleOpportunity == null) {
            return ResponseCodeEnum.ERROR;
        }
        saleOpportunityMapper.updateByPrimaryKeySelective(saleOpportunity);
        return ResponseCodeEnum.SUCCESS;
    }


    /**
     * 指派销售机会
     * @param saleOpportunity
     * @return
     */
    @Override
    public ResponseCodeEnum appointSalesAssigned(SaleOpportunity saleOpportunity) {
        if (saleOpportunity == null) {
            return ResponseCodeEnum.ERROR;
        }
        saleOpportunityMapper.updateByPrimaryKey(saleOpportunity);
        return ResponseCodeEnum.SUCCESS;
    }

    /**
     * 删除指定销售机会
     * @param saleOpportunity
     * @return
     */
    @Override
    public ResponseCodeEnum dropSalesAssigned(SaleOpportunity saleOpportunity) {
        if (saleOpportunity == null) {
            return ResponseCodeEnum.ERROR;
        }
        saleOpportunityMapper.deleteByPrimaryKey(saleOpportunity.getId());
        return ResponseCodeEnum.SUCCESS;
    }

    /**
     * 根据ID进行查询
     * @param id 销售机会的ID
     * @return
     */
    @Override
    public SaleOpportunity querySaleOpportunityById(int id) {
        SaleOpportunity saleOpportunity = saleOpportunityMapper.selectByPrimaryKey(id);
        return saleOpportunity;
    }


}
