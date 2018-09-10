package nuc.jyg.crm.model;

import lombok.*;

/**
 * @author aolish333@gmail.com
 * @date 2018/9/10 15:28
 * User:Lee
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanTime {
    private String  createTime;

    private Integer saleId;

    private String planContent;

    private String planResult;

    /**
     * 销售ID
     */
    private String id;

}
