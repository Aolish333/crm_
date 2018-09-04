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
public class Product {

    private Integer id;

    private String productName;

    private String produceModel;

    private String levelBatch;

    private String unit;

    private Long unitPrice;

    private String remark;

    private String warehouse;

    private String location;

    private Integer quantity;

    private Date createTime;

    private Date updateTime;

}