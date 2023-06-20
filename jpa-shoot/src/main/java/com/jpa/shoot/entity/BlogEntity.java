package com.jpa.shoot.entity;

import com.jpa.shoot.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private StatusEnum activeStatus;

    private Character isDeleted;

    private LocalDateTime lastUpdatedOn;

    private LocalDate createdDate;

    private LocalTime createdTime;

    private Boolean isPrivate;

    private BigDecimal price;
}
