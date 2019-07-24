package com.lqh.dev.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Account {
    private Long id;
    private String userId;
    private String name;
    private BigDecimal balance;
    private Date createTime;
    private Date updateTime;
    private Integer version;
}
