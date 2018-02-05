package com.form3.exercise.cramos.web;

public class APIResponse<T> {

    private T data;

    /**
     * Response Constructor.
     * @param data
     */
    public APIResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override public String toString() {
        return "APIResponse{" + "data=" + data + '}';
    }
}
