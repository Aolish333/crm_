package nuc.jyg.crm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Ji YongGuang.
 * @date 16:53 2018/9/06.
 */
@Setter
@Getter
@ToString
public class CustomerVO {

    private Integer id;
    private String usernmae;
    private String managerName;
    private BigDecimal orderAmount;
    private String lossReason;
}
