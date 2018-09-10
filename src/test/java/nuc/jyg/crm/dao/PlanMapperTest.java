package nuc.jyg.crm.dao;

import nuc.jyg.crm.model.Plan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/10 8:52
 * User:Lee
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanMapperTest {
    @Autowired
    PlanMapper planMapper;
    @Test
    public void selectBySid() throws Exception {
        System.out.println("*********");
        List<Plan> plans = planMapper.selectBySid(3);
        System.out.println(plans.toString());
    }

}