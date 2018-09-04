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
public class Plan {

    private Integer id;

    private Integer saleId;

    private String planContent;

    private String planResult;

    private Date createTime;

    private Date updateTime;
}