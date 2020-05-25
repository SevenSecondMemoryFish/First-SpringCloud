package com.seven.second.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommentResult(Integer code, String message) {
        this(code, message, null);
    }

    public CommentResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
