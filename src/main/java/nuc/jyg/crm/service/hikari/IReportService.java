package nuc.jyg.crm.service.hikari;


import nuc.jyg.crm.vo.CustomerLossVO;
import nuc.jyg.crm.vo.CustomerVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ji YongGuang.
 * @date 16:43 2018/8/16.
 * 客户报表业务接口
 */
public interface IReportService {

    /**
     * 获取客户贡献分析
     *
     * @param customerName 客户名称
     * @param years        年份
     * @return 返回 客户名称及其年份内的订单总金额
     */
    List<CustomerVO> getCustomerContribution(String customerName, String years) throws SQLException;

    /**
     * 获取客户构成分析
     *
     * @param manner 报表方式
     * @return 返回 客户不同报表方式下的 客户人员占有数量
     */
    Map<String, Integer> getCustomerConstract(String manner) throws SQLException;

    /**
     * 获取客户流失分析
     *
     * @param username 客户名称
     * @param manager 客户经理名称
     * @return 返回 客户流失信息
     */
    ArrayList<CustomerLossVO> getCustomerLoss(String username, String manager) throws SQLException;


    Map<String, Integer> getServices(String year);

}
