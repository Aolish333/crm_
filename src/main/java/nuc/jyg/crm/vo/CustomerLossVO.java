package nuc.jyg.crm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ji YongGuang.
 * @date 16:53 2018/9/06.
 */
@Setter
@Getter
@ToString
public class CustomerLossVO {

    private Integer id;
    private String year;
    private String customerName;
    private String managerName;
    private String lossReason;
}
