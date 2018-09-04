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
public class Service {

    private Integer id;

    private String serviceNumber;

    private String kind;

    private String summary;

    private String customerName;

    private Byte status;

    private String serviceRequest;

    private String founder;

    private String customerManager;

    private Date distributionTime;

    private String serviceProcess;

    private String processerName;

    private Date processTime;

    private Date createTime;

    private Date updateTime;

    private String processResult;

    private Byte processSatisfaction;
}