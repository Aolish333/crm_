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
public class Order {
    
    private Integer id;

    private Long orderNo;

    private Integer customerId;

    private String customerName;

    private Integer status;

    private String address;

    private Date createTime;

    private Date updateTime;
}