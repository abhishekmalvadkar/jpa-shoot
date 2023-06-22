package com.jpa.shoot.repository;

import com.jpa.shoot.entity.BlogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class BlogRepository {

    private final EntityManager em;


    /**
     * if caller method has @Transactional then it will join it and share the same PC
     * if caller does not have @Transactional then it will create new PC or transaction
     * and this PC will be destroyed at the end of this method
     */
    @Transactional
    public void update(BlogEntity blogEntity) {
        this.em.merge(blogEntity);
    }

    @Transactional
    public void save(BlogEntity blogEntity) {
        log.info("This will print before INSERT query creation");
        this.em.persist(blogEntity);
        log.info("This will print after INSERT query prepared");
    }


    @Transactional
    public void persistenceContextBehaviourTesting(BlogEntity blogEntity) {
        this.em.persist(blogEntity);
        Integer newBlogId = blogEntity.getId();
        log.info("New blog id :: {}", newBlogId);
        BlogEntity savedBlogEntity = this.em.find(BlogEntity.class, newBlogId);
        savedBlogEntity.setContent("I am changing here");
    }

    public BlogRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteById(Integer id) {
        BlogEntity blogEntity = this.em.find(BlogEntity.class, id);
        this.em.remove(blogEntity);
    }

    @Transactional(readOnly = true)
    public BlogEntity findById(Integer id) {
        BlogEntity blogEntity = this.em.find(BlogEntity.class, id);
        return blogEntity;
    }


}
