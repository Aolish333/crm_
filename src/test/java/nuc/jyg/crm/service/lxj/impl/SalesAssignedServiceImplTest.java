package nuc.jyg.crm.service.lxj.impl;

import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.service.lxj.SalesAssignedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/7 10:49
 * User:Lee
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesAssignedServiceImplTest {
    @Autowired
    SalesAssignedService salesAssignedService;
    @Test
    public void querySaleOpportunityByStatus() throws Exception {
        List<SaleOpportunity> saleOpportunities = salesAssignedService.querySaleOpportunityByStatus((byte) 1);
        System.out.println("_________\n"+saleOpportunities.toString());
    }

}