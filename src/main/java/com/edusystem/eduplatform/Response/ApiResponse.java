package com.edusystem.eduplatform.Response;

import lombok.Data;

@Data

public class ApiResponse {
    private int code;
    private String message;
    private Object data;
}
