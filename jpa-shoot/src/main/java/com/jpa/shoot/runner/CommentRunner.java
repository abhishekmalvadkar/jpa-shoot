package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import com.jpa.shoot.entity.CommentEntity;
import com.jpa.shoot.exception.RecordNotFoundException;
import com.jpa.shoot.repository.BlogRepository;
import com.jpa.shoot.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentRunner implements CommandLineRunner {

    private final EntityManager em;

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {



    }

    public void fetchCommentsWithPaginationExample() {
        Integer blogId = 25;
        Integer pageNo = 10;
        Integer pageSize = 2;
        List<CommentEntity> comments = this.commentRepository.fetchPaginatedComments(blogId,pageNo,pageSize);
        for (CommentEntity comment : comments) {
            log.info("Comment id : {}" , comment.getId());
            log.info("Comment text : {}" , comment.getText());
        }
    }

    public void commentFetchWithoutPaginationExample() {
        Integer blogId = 25;
        List<CommentEntity> comments = this.commentRepository.fetchCommentsWithoutPagination(blogId);
        for (CommentEntity comment : comments) {
            log.info("Comment text : {}" , comment.getText());
        }
    }

    @Transactional
    public void commentUpdateExample() {
        Integer commentId = 1;
        String newCommentText = "Very Bad Blog";

        CommentEntity dbCommentObject = this.em.find(CommentEntity.class, commentId);
        if (Objects.nonNull(dbCommentObject)){
            dbCommentObject.setText(newCommentText);
        } else {
            throw new RecordNotFoundException("Comment does not exists with id : " + commentId);
        }
    }

    @Transactional
    public void commentPersistExample() {
        Integer blogId = 25;
        BlogEntity blogEntity = this.blogRepository.findById(blogId);
        if (Objects.isNull(blogEntity)){
            throw new RecordNotFoundException("Blog does not exists with id : " + blogId);
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText("Very Good Explanation");
        commentEntity.setBlogEntity(blogEntity);

        this.em.persist(commentEntity);
    }
}
