package com.jpa.shoot.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class ApiResponse<T> {

    private T data;

    private HttpStatus status;

    public int getStatusCode(){
        return status.value();
    }

}
