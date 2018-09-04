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
public class Contact {

    private Integer id;

    private Integer customerId;

    private String name;

    private Byte sex;

    private String position;

    private String officePhone;

    private String mobilePhone;

    private String remarks;

    private Date createTime;

    private Date updateTime;
}