package com.email.send.dto;

public class GenericResponseDTO {

    public String code;

    public String message;

    public GenericResponseDTO() {

    }

    public GenericResponseDTO(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
