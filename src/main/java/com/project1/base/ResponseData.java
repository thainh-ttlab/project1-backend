package com.project1.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData<T> implements Serializable {
    private int code;
    private String message;
    private T data;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone ="GMT+7")
	private Date dataTime = new Date();

    public ResponseData() {

    }

    public ResponseData(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData<T> success(T data) {
        this.code = 200;
        this.data = data;
        this.message = "success";
        return this;
    }

    public ResponseData<T> error(T data) {
        this.code = -1;
        this.data = data;
        this.message = "error";
        return this;
    }
}
