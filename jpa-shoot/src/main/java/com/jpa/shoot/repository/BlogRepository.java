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
    public void save(BlogEntity blogEntity){
        this.em.persist(blogEntity);
    }

    public BlogEntity findById(Integer id){
        BlogEntity blogEntity = this.em.find(BlogEntity.class, id);
        return blogEntity;
    }
}
