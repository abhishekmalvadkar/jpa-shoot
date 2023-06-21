package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import com.jpa.shoot.enums.StatusEnum;
import com.jpa.shoot.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@Slf4j
public class JpaTestRunner implements CommandLineRunner {

    private final BlogRepository blogRepository;

    public JpaTestRunner(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Integer blogId = 3;
        BlogEntity blogEntity = this.blogRepository.findById(blogId);
        this.blogRepository.deleteById(blogId);
    }

    private void entityManagerMergeExample() {
        BlogEntity blogEntity = this.blogRepository.findById(1);
        blogEntity.setContent("Updated");
        this.blogRepository.update(blogEntity);
    }

    private void entityManagerFindExample() {
        Integer blogId = 1;
        BlogEntity blogEntity = this.blogRepository.findById(blogId);
        log.info("Blog name :: {}" , blogEntity.getContent());
    }

    private void entityManagerPersistExample() {
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
