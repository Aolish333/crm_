package nuc.jyg.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    
    private Integer id;

    private Integer customerId;

    private Long orderNo;

    private Integer productId;

    private String productName;

    private Integer quantity;

    private String unit;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private Date createTime;

    private Date updateTime;
}