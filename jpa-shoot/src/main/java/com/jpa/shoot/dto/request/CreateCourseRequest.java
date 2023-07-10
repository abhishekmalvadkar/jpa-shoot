package com.jpa.shoot.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateCourseRequest {

    private String name;

    private BigDecimal price;

}
