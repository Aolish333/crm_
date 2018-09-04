package nuc.jyg.crm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ji YongGuang.
 * @date 10:20 2018/09/04.
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 错误
     */
    ERROR(1, "ERROR");

    private final int code;
    private final String desc;
}