package com.umc3.umc3_demo.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Throwable {
    private BaseResponseStatus status;
}
