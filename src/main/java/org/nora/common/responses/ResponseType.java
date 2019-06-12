package org.nora.common.responses;

import lombok.Data;
import org.nora.common.constlant.CommonConstant;

import java.io.Serializable;

@Data
public class ResponseType<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private T data;
    private long timestamp = System.currentTimeMillis();

    public ResponseType() {
        this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
    }

    public ResponseType(String s) {
        this.msg = s;
        this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
    }
    public ResponseType self(){
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseType setMsg(String msg) {
        this.msg = msg;
        return self();
    }

    public T getData() {
        return data;
    }

    public ResponseType setData(T data) {
        this.data = data;
        return self();
    }

    public ResponseType success(String msg) {
        this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
        this.msg = msg;
        return self();
    }

    public ResponseType success() {
        this.code = CommonConstant.ServerApp.SERVER_RET_SUSSCESS;
        return self();
    }

    public ResponseType success(T data) {
        this.data=data;
        return this.success();
    }

    public ResponseType failure(String msg) {
        this.code = CommonConstant.ServerApp.SERVER_RET_FAILURE;
        this.msg = msg;
        return self();
    }
    public ResponseType failure(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
        return self();
    }

}
