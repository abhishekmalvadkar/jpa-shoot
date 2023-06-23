package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaEntityManagerMethodsInvestigationRunner implements CommandLineRunner {

    private final EntityManager em;

    @Override
    public void run(String... args) throws Exception {

        TypedQuery<BlogEntity> q = this.em.createNamedQuery("fetchAllBlogs", BlogEntity.class);
        List<BlogEntity> blogs = q.getResultList();
        for (BlogEntity blog : blogs) {
            log.info("Blog :: {}", blog);
        }


    }

    @Transactional
    public void createQueryExampleWithBulkUpdateCase() {
        /**
         * Suppose from UI user will select multiple blogs using checkboxes and those
         * blogs he wants to make private so let's see how we can handle this bulk update
         *
         */
        List<Integer> blogIdsToBeMakePrivate = List.of(11 , 12 , 13);
        Query q = this.em.createQuery("UPDATE BlogEntity b SET b.isPrivate=true WHERE b.id IN :blogIds");
        q.setParameter("blogIds" , blogIdsToBeMakePrivate);
        int noOgBlogsUpdated = q.executeUpdate();
        log.info("{} no of blogs updated successfully" , noOgBlogsUpdated);
    }

    @Transactional
    public void createQueryWithBulkUpdateExample() {
        /**
         * Suppose from UI user will select multiple blogs using checkboxes and those
         * blogs he wants to delete then how we can handle that bulk delete operation let
         * us see
         */
        List<Integer> blogIdsToBeDeleted = List.of(8, 9, 10);
        Query q = this.em.createQuery("DELETE FROM BlogEntity b WHERE b.id IN :blogIds");
        q.setParameter("blogIds", blogIdsToBeDeleted);
        int noOfBlogsDeleted = q.executeUpdate();
        log.info("{} of blogs deleted successfully", noOfBlogsDeleted);
    }

    public void createQueryExampleWithJpqlAndResponseTypeAsArgument() {
        /**
         * Here no need to type cast because we have told to JPA the response type class
         */
        TypedQuery<BlogEntity> q = this.em.createQuery("SELECT b from BlogEntity b", BlogEntity.class);
        List<BlogEntity> blogs = q.getResultList();
        for (BlogEntity blog : blogs) {
            log.info("Blog :: {}", blog);
        }
    }

    public void createQueryExampleWithOnlyJpqlQueryAsArgument() {
        /**
         * This version of createQuery() method will give list of object type in order to use it
         * we have to type cast into blog entity object
         */
        Query q = this.em.createQuery("SELECT b from BlogEntity b");
        var blogs = q.getResultList();
        for (Object blogAsObjectType : blogs) {
            /**
             * Need to type cast
             */
            BlogEntity blog = (BlogEntity) blogAsObjectType;
            log.info("Blog :: {}", blog);
        }
    }
}
