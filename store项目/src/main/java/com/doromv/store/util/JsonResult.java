package com.doromv.store.util;

import java.io.Serializable;

/**
 * @author Doromv
 * @create 2022-03-30-9:15
 */
public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    private E data;
    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
