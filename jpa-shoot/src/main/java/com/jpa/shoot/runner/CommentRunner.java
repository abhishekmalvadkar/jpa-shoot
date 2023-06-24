package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import com.jpa.shoot.entity.CommentEntity;
import com.jpa.shoot.exception.RecordNotFoundException;
import com.jpa.shoot.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentRunner implements CommandLineRunner {

    private final EntityManager em;

    private final BlogRepository blogRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

    }

    private void commentPersistExample() {
        Integer blogId = 25;
        BlogEntity blogEntity = this.blogRepository.findById(blogId);
        if (Objects.isNull(blogEntity)){
            throw new RecordNotFoundException("Blog does not exists with id : " + blogId);
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText("Nice blog");
        commentEntity.setBlogEntity(blogEntity);

        this.em.persist(commentEntity);
    }
}
