package com.lqh.dev.model;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private T data;
    private String code;
    private String msg;
}
