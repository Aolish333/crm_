package nuc.jyg.crm.model;

import lombok.*;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/10 18:18
 * User:Lee
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleOpportunityTime {

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

    private String createTime;

    private String owner;

    private String assignTime;

    private Byte status;

    private String updateTime;
}
