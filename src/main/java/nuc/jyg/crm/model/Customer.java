package nuc.jyg.crm.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer id;

    private String number;

    private String customerName;

    private String contactName;

    private String contactPhone;

    private Byte status;

    private String area;

    private String manager;

    private Byte grade;

    private Byte satisfaction;

    private Byte credit;

    private String address;

    private Integer mail;

    private String fax;

    private String url;

    private String registrationNumber;

    private String legalPerson;

    private Integer registeredCapital;

    private Long annualTurnover;

    private String blank;

    private String blankNumber;

    private String localtaxNumber;

    private String nationaltaxNumber;

    private Date createTime;

    private Date updateTime;
}