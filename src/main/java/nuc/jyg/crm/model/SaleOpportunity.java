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
public class SaleOpportunity {

    private Integer id;

    private Integer number;

    private String source;

    private String customerName;

    private Byte chance;

    private String summary;

    private String contact;

    private String contactPhone;

    private String opportunityDescription;

    private String founder;

    private Date createTime;

    private String owner;

    private Date assignTime;

    private Byte status;

    private Date updateTime;

}