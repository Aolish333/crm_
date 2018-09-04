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
public class Employee {
    
    private Integer id;

    private String name;

    private String password;

    private Integer role;

    private Date createTime;

    private Date updateTime;
}