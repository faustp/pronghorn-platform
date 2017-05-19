package com.pccw.ad.pronghorn.ws.reponse;

/**
 * Created by FaustineP on 06/04/2017.
 */
public class ResponseMessage<T> {

    private int code;
    private String message;
    private T result;

    public ResponseMessage(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
