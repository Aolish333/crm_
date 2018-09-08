package nuc.jyg.crm.controller;

import nuc.jyg.crm.service.hikari.IReportService;
import nuc.jyg.crm.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Ji YongGuang.
 * @date 16:37 2018/9/6.
 */
@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private IReportService iReportService;


    @GetMapping(value = "/contribution")
    public String getContributions(Model model) throws SQLException {

        List<CustomerVO> customerVOS = iReportService.getCustomerContribution(null, null);
        model.addAttribute("customerVOS", customerVOS);
        return "contribution-analysis";
    }

    @GetMapping(value = "/constitute")
    public String customerConstitute() {

        return "constitute-analysis";
    }


    @GetMapping(value = "/service")
    public String customerService() {

        return "service-analysis";
    }

    @GetMapping(value = "/loss")
    public String customerLoss() {

        return "lost-analysis";
    }
}
