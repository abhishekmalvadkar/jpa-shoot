package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import com.jpa.shoot.enums.StatusEnum;
import com.jpa.shoot.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class JpaTestRunner implements CommandLineRunner {

    private final BlogRepository blogRepository;

    public JpaTestRunner(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setActiveStatus(StatusEnum.ACTIVE);
        blogEntity.setContent("This is spring boot NEW");
        blogEntity.setTitle("Intro fo spring boot NEW");
        blogEntity.setPrice(BigDecimal.valueOf(100));
        blogEntity.setIsDeleted('N');
        blogEntity.setIsPrivate(false);
        blogEntity.setCreatedDate(LocalDate.now());
        blogEntity.setCreatedTime(LocalTime.now());
        blogEntity.setLastUpdatedOn(LocalDateTime.now());
        this.blogRepository.save(blogEntity);
    }
}
