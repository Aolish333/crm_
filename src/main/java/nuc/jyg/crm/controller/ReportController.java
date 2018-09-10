package nuc.jyg.crm.controller;

import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.service.hikari.IReportService;
import nuc.jyg.crm.vo.CustomerLossVO;
import nuc.jyg.crm.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.*;

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
    public String getContributions(@RequestParam(value = "year", required = true, defaultValue = "全部") String year,
                                   String customerName, Model model) throws SQLException {

        List<CustomerVO> customerVOS = iReportService.getCustomerContribution(customerName, year);
        model.addAttribute("customerVOS", customerVOS);
        model.addAttribute("customerName", customerName);
        return "contribution-analysis";
    }

    @GetMapping(value = "/constitute")
    public String customerConstitute(@RequestParam(value = "manner", required = true, defaultValue = "按等级") String
                                             manner, Model model) {

        Map<String, Integer> map = null;
        try {
            map = iReportService.getCustomerConstract(manner);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<Integer, Map.Entry> maps = new HashMap<>();

        Iterator iterator = map.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            maps.put(++index, m);
        }

        Const.ClassificationQueryEnum[] classificationQueryEnums = Const.ClassificationQueryEnum.values();
        ArrayList<String> mannerList = new ArrayList<>(10);
        for (Const.ClassificationQueryEnum targetManner :
                classificationQueryEnums) {
            mannerList.add(targetManner.getValue());
        }

        model.addAttribute("mannerList", mannerList);
        model.addAttribute("manner", manner);
        model.addAttribute("result", maps);
        return "constitute-analysis";
    }


    @GetMapping(value = "/service")
    public String customerService(@RequestParam(value = "year", required = false, defaultValue = "全部") String year,
                                  Model model) {

        // TODO 统计数量
        Map<String, Integer> services = iReportService.getServices(year);

        Const.ServiceYearEnum[] serviceYearEnums = Const.ServiceYearEnum.values();
        ArrayList<String> yearList = new ArrayList<>(10);
        for (Const.ServiceYearEnum targetYear :
                serviceYearEnums) {
            yearList.add(targetYear.getValue());
        }

        // TODO 生成编号
        Map<Integer, Map.Entry<String, Integer>> maps = new HashMap<Integer, Map.Entry<String, Integer>>(10, 0.75f);
        Iterator iterator = services.entrySet().iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry<String, Integer>) iterator.next();
            maps.put(++index, entry);
        }

        model.addAttribute("year", year);
        model.addAttribute("yearList", yearList);
        model.addAttribute("services", maps);
        return "service-analysis";
    }

    @GetMapping(value = "/loss")
    public String customerLoss(String customerName, String managerName, Model model) {

        ArrayList<CustomerLossVO> customerLossVOS = null;// 2006 - ***
        try {
            customerLossVOS = iReportService.getCustomerLoss(customerName, managerName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("customerName", customerName);
        model.addAttribute("managerName", managerName);
        model.addAttribute("result", customerLossVOS);
        return "lost-analysis";
    }
}
