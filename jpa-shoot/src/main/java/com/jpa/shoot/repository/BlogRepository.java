package com.jpa.shoot.repository;

import com.jpa.shoot.entity.BlogEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class BlogRepository {

    private final EntityManager em;

    public BlogRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void deleteById(Integer id){
        BlogEntity blogEntity = this.em.find(BlogEntity.class, id);
        this.em.remove(blogEntity);
    }

    @Transactional
    public void update(BlogEntity blogEntity){
        this.em.merge(blogEntity);
    }

    @Transactional
    public void save(BlogEntity blogEntity){
        this.em.persist(blogEntity);
    }

    @Transactional(readOnly = true)
    public BlogEntity findById(Integer id){
        BlogEntity blogEntity = this.em.find(BlogEntity.class, id);
        return blogEntity;
    }
}
