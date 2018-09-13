package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.SaleOpportunity;
import nuc.jyg.crm.util.DateTimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/12 22:00
 * User:Lee
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOpportunityMapperTest {
    @Autowired
    SaleOpportunityMapper saleOpportunityMapper;

    @Test
    public void selectByTime() throws Exception {
     List<SaleOpportunity> saleOpportunities =  saleOpportunityMapper.selectByTime(null,DateTimeUtil.strToDate("2017-3-4 09:25:13"),DateTimeUtil.strToDate("2018-9-12 09:25:13"), (byte) 1,null);
//     List<SaleOpportunity> saleOpportunities =  saleOpportunityMapper.selectByTime(null,"2017-3-4","2018-9-12", (byte) 1,null);
        System.out.println("000000000"+saleOpportunities.toString());
    }

}