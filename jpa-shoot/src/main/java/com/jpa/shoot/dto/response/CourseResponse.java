package com.jpa.shoot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseResponse {

    private  Integer  id;
    private String name;
    private BigDecimal price;
}
