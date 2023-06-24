package com.jpa.shoot.repository;

import com.jpa.shoot.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public List<CommentEntity> fetchCommentsWithoutPagination(Integer blogId) {

        TypedQuery<CommentEntity> q = this.em
                .createQuery("SELECT c FROM CommentEntity c WHERE c.blogEntity.id = :blogId",
                        CommentEntity.class);
        q.setParameter("blogId", blogId);

        List<CommentEntity> comments = q.getResultList();
        return comments;
    }

    public List<CommentEntity> fetchPaginatedComments(Integer blogId,
                                                      Integer pageNumber, Integer pageSize) {
        TypedQuery<CommentEntity> q = this.em
                .createQuery("SELECT c FROM CommentEntity c WHERE c.blogEntity.id = :blogId",
                        CommentEntity.class);
        q.setParameter("blogId", blogId);

        /**
         * Page size = 5
         *
         * first result means how many item we need to skip , think it is as offset of mysql query
         * Page 1 => first result = 0; => (5 * 1) - 5
         * Page 2 => first result = 5; => (5 * 2) - 5
         * Page 3 => first result = 10; => (5 * 3) - 5
         *
         * Logic => (PageSize * PageNo) - PageSize
         */
        int skipRecords = (pageSize * pageNumber) - pageSize;
        q.setFirstResult(skipRecords);
        q.setMaxResults(pageSize);

        List<CommentEntity> comments = q.getResultList();
        return comments;
    }

}
