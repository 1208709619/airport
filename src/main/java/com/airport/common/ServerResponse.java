package com.airport.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status =status;
    }

    private ServerResponse(int status,T data){
        this.status =status;
        this.data =data;
    }

    private ServerResponse(int status,String msg, T data){
        this.status =status;
        this.msg=msg;
        this.data=data;
    }

    private ServerResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }


    @JsonIgnore
    public  boolean isSuccess(){
        return  this.status==ResponseCode.SUCCESS.getCode();
    }

    public  int getStatus(){
        return this.status;
    }

    public T getData(){
        return  this.data;
    }

    public String getMsg(){
        return this.msg;
    }


    public static <T> ServerResponse<T> success(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> success(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> success(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }


    public static <T> ServerResponse<T> success(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> fail(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> fail(String msg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServerResponse<T> fail(int errorCode,String errorMsg){
        return new ServerResponse<T>(errorCode,errorMsg);
    }

}
