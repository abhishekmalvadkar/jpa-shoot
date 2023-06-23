package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
@RequiredArgsConstructor
@Slf4j
public class JpaSelectApproachesInvestigationRunner implements CommandLineRunner {

    private final EntityManager em;

    @Override
    public void run(String... args) throws Exception {
        createQueryExampleWithOnlyJpqlQueryAsArgument();

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
