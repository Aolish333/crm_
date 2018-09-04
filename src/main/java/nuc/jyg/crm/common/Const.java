package nuc.jyg.crm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Ji YongGuang.
 * @date 16:27 2018/09/03.
 */
@NoArgsConstructor
public final class Const {

    /**
     * 系统对用户n个月未下单的检查触发时间
     */
    public static final int EARLY_WARNING_TIME = 6;

    /**
     * 付款状态
     */
    public interface PaymentStatusEnum {
        /**
         * 已付款
         */
        int ALREADY_PAID = 20;
        /**
         * 未付款
         */
        int UNPAID = 10;
    }

    /**
     * 客户流失记录的状态
     */
    public interface CustomerLossStatus {
        /**
         * 确认流失
         */
        int CONFIRM_LOSS = 0;
        /**
         * 暂缓流失
         */
        int SUSPEND_LOSS = 1;
    }

    /**
     * 客户的状态
     */
    public interface CustomerStatus {
        /**
         * 正常
         */
        int NORMAL = 0;
        /**
         * 已流失
         */
        int ALREADY_LOSS = 1;
    }

    /**
     * 系统人员枚举
     */
    @Getter
    @AllArgsConstructor
    public enum SystemUserEnum {
        SYSTEM_MANAGER_USER(0, "系统管理员"),
        SALES_DIRECTOR(10, "销售主管"),
        CUSTOMER_MANAGER(20, "客户经理"),
        EXECUTIVES(30, "高管");

        private int code;
        private String value;
    }

    /**
     * 销售机会状态枚举
     */
    @Getter
    @AllArgsConstructor
    public enum SaleOpportunityStatusEnum {
        ALLOCATED(0, "已分配"),
        UNDISTRIBUTED(1, "未分配"),
        DEVELOPMENT_SUCCESSFUL(2, "开发成功"),
        DEVELOPMENT_FAILURE(3, "开发失败");

        private int code;
        private String value;
    }

    /**
     * 客户等级
     */
    @AllArgsConstructor
    @Getter
    public enum CustomerGradeEnum {

        GRADE_STRATEGIC_PARTNERS(7, "战略合作伙伴"),
        GRADE_PARTNERS(8, "合作伙伴"),
        GRADE_BIG_CUSTOMER(9, "大客户"),
        GRADE_KEY_CUSTOMERS(10, "重点开发客户"),
        GRADE_ORDINARY_CUSTOMER(11, "普通客户");

        private int code;
        private String value;
    }

    /**
     * 客户信用度
     */
    @AllArgsConstructor
    @Getter
    public enum CustomerCreditEnum {

        CREDIT_THREE_STARS(3, "三颗星"),
        CREDIT_FOUR_STARS(4, "四颗星"),
        Credit_FIVE_STARS(5, "五颗星");

        private int code;
        private String value;
    }

    /**
     * 客户满意度
     */
    @AllArgsConstructor
    @Getter
    public enum CustomerSatisfactionEnum {

        SATISFACTION_ONE_STARS(1, "一颗星"),
        SATISFACTION_TWO_STARS(2, "二颗星"),
        SATISFACTION_THREE_STARS(3, "三颗星"),
        SATISFACTION_FOUR_STARS(4, "四颗星"),
        SATISFACTION_FIVE_STARS(5, "五颗星");

        private int code;
        private String value;
    }

    /**
     * 流失原因
     */
    @AllArgsConstructor
    @Getter
    public enum CauseOfLossEnum {

        REASON_MIGRATE(10, "客户厂址迁移"),
        REASON_PURCHASE(20, "客户公司被收购"),
        REASON_NO_DEMAND(30, "没有采购需求");

        private int code;
        private String value;
    }

    /**
     * 种类查询枚举
     */
    @Getter
    @AllArgsConstructor
    public enum ClassificationQueryEnum {
        CUSTOMER_LEVEL(0, "按等级"),
        CREDIT(1, "按信用度"),
        SATISFACTION(2, "按满意度");

        private int code;
        private String value;
    }

    /**
     * 服务类型枚举
     */
    @Getter
    @AllArgsConstructor
    public enum ServiceTypeEnum {

        CUSTOMER_LEVEL(0, "咨询"),
        CREDIT(1, "建议"),
        SATISFACTION(2, "投诉");

        private int code;
        private String value;
    }

    /**
     * 服务状态枚举
     */
    @Getter
    @AllArgsConstructor
    public enum ServiceStatusEnum {

        NEWLY_CREATED(0, "新创建"),
        ALLOCATED(1, "已分配"),
        PROCESSED(2, "已处理"),
        ARCHIVED(3, "已归档");

        private int code;
        private String value;
    }
}
