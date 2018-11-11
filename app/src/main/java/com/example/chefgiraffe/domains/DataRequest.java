package com.example.chefgiraffe.domains;

public class DataRequest<T> {
    private T data;
    private boolean isSuccessful;
    private String message;

    public DataRequest(T data) {
        this.data = data;
        message = "";
        isSuccessful = true;
    }

    public DataRequest(String message) {
        data = null;
        this.isSuccessful = false;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }
}
