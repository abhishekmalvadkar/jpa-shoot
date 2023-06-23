package com.jpa.shoot.entity;

import com.jpa.shoot.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@ToString
@NamedQuery(name = "fetchAllBlogs" , query = "SELECT b from BlogEntity b")
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
