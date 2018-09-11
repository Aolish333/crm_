package nuc.jyg.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import nuc.jyg.crm.common.Const;
import nuc.jyg.crm.service.hikari.IReportService;
import nuc.jyg.crm.vo.ConstituteEchartsVO;
import nuc.jyg.crm.vo.CustomerLossVO;
import nuc.jyg.crm.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Ji YongGuang.
 * @date 16:37 2018/9/6.
 */
@SuppressWarnings("unchecked")
@Log4j2
@Controller
@RequestMapping(value = "/report")
public class ReportController {

    private static Map<String, Object> method_response = new HashMap<String, Object>(10, 0.75f);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IReportService iReportService;

    @GetMapping(value = "/contribution")
    public String getContributions(@RequestParam(value = "year", required = true, defaultValue = "全部") String year,
                                   String customerName, Model model) throws SQLException {

        ArrayList<CustomerVO> customerVOS = (ArrayList<CustomerVO>) iReportService.getCustomerContribution
                (customerName, year);

        model.addAttribute("customerVOS", customerVOS);
        model.addAttribute("customerName", customerName);

        // todo 填充contribution的echarts全局内容
        Map<String, ArrayList<String>> report = new HashMap<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> decimals = new ArrayList<>();
        for (CustomerVO customerVO : customerVOS) {
            names.add(customerVO.getUsernmae());
            decimals.add(String.valueOf(customerVO.getOrderAmount()));
        }
        report.put("x", names);
        report.put("value", decimals);
        method_response.put("contribution", report);
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
        // todo Constitute的echarts全局容器内容
        ArrayList<ConstituteEchartsVO> constituteEchartsVOS = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            maps.put(++index, m);
            // todo 填充Constitute的echarts的Vo对象
            ConstituteEchartsVO constituteEchartsVO = new ConstituteEchartsVO();
            constituteEchartsVO.setValue((Integer) m.getValue());
            constituteEchartsVO.setName(String.valueOf(m.getKey()));
            constituteEchartsVOS.add(constituteEchartsVO);
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
        // todo 装载Constitute的echarts全局容器
        method_response.put("constitute", constituteEchartsVOS);
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

    @RequestMapping(value = "/page/contribution")
    public String pageContribution() {// 返回视图
        return "echarts_contribution";
    }

    @ResponseBody
    @PostMapping(value = "/data/contribution")
    public String dataContribution() throws JsonProcessingException {// AJAX返回数据
        log.info(objectMapper.writeValueAsString(method_response.get("contribution")));
        return objectMapper.writeValueAsString(method_response.get("contribution"));
    }

    @RequestMapping(value = "/page/constitute")
    public String pageConstitute() {
        return "echarts_constitute";
    }

    @ResponseBody
    @PostMapping(value = "/data/constitute")
    public String dataConstitute() throws JsonProcessingException {// AJAX返回数据
        log.info(objectMapper.writeValueAsString(method_response.get("constitute")));
        return objectMapper.writeValueAsString(method_response.get("constitute"));
    }

    @ResponseBody
    @PostMapping(value = "/data/constitute/credit")
    public String dataConstituteCredit() throws JsonProcessingException {// AJAX返回数据

        String manner = "按信用度";
        Map<String, Integer> map = null;
        try {
            map = iReportService.getCustomerConstract(manner);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Iterator iterator = map.entrySet().iterator();
        int index = 0;
        // todo Constitute的echarts全局容器内容
        ArrayList<ConstituteEchartsVO> constituteEchartsVOS = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            // todo 填充Constitute的echarts的Vo对象
            ConstituteEchartsVO constituteEchartsVO = new ConstituteEchartsVO();
            constituteEchartsVO.setValue((Integer) m.getValue());
            constituteEchartsVO.setName(String.valueOf(m.getKey()));
            constituteEchartsVOS.add(constituteEchartsVO);
        }

        // todo 装载Constitute的echarts全局容器
        method_response.put("credit", constituteEchartsVOS);
        log.info(objectMapper.writeValueAsString(method_response.get("credit")));
        return objectMapper.writeValueAsString(method_response.get("credit"));
    }

    @ResponseBody
    @PostMapping(value = "/data/constitute/satisfaction")
    public String dataConstituteSatisfaction() throws JsonProcessingException {// AJAX返回数据

        String manner = "按满意度";
        Map<String, Integer> map = null;
        try {
            map = iReportService.getCustomerConstract(manner);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Iterator iterator = map.entrySet().iterator();
        int index = 0;
        // todo Constitute的echarts全局容器内容
        ArrayList<ConstituteEchartsVO> constituteEchartsVOS = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            // todo 填充Constitute的echarts的Vo对象
            ConstituteEchartsVO constituteEchartsVO = new ConstituteEchartsVO();
            constituteEchartsVO.setValue((Integer) m.getValue());
            constituteEchartsVO.setName(String.valueOf(m.getKey()));
            constituteEchartsVOS.add(constituteEchartsVO);
        }

        // todo 装载Constitute的echarts全局容器
        method_response.put("satisfaction", constituteEchartsVOS);
        log.info(objectMapper.writeValueAsString(method_response.get("satisfaction")));
        return objectMapper.writeValueAsString(method_response.get("satisfaction"));
    }
}
