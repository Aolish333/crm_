package nuc.jyg.crm.model;

import lombok.*;

import java.util.Date;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    private Integer id;

    private Integer saleId;

    private String planContent;

    private String planResult;

    private Date createTime;

    private Date updateTime;
}