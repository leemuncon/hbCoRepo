package xyz.leefly.project.web.model;

import lombok.Data;

@Data
public class RespData<T> {

    private int code;

    private boolean success;

    private String msg;

    private T data;

}
