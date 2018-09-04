package nuc.jyg.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoss {
    
    private Integer id;

    private String lossNumber;

    private String customerName;

    private String customerManager;

    private Date lastOrderTime;

    private Date confirmLossTime;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private String measures;

    private String lossReason;
}