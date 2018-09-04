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
public class Socialize {

    private Integer id;

    private Integer customerId;

    private String address;

    private String summary;

    private String remarks;

    private String details;

    private Date createTime;

    private Date updateTime;
}