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
public class DataDictionary {

    private Integer id;

    private String dataNumber;

    private String kind;

    private String entry;

    private String value;

    private Byte editable;

    private Date createTime;

    private Date updateTime;
}