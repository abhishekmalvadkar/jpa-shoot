package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import com.jpa.shoot.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class JpaUpdateInvestigationRunner implements CommandLineRunner {

    private final EntityManager em;

    private final BlogRepository blogRepository;

    public JpaUpdateInvestigationRunner(EntityManager em, BlogRepository blogRepository) {
        this.em = em;
        this.blogRepository = blogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Integer blogId = 4;
        /**
         * PC will start at LINE 22 and will end the same line and dbBlogObject will be detached from PC
         * and will be in detached state
         * because we didn't put @Transactional annotation on
         * our run method
         */
        BlogEntity dbBlogObject = this.em.find(BlogEntity.class, blogId);
        /**
         * This LINE 29 change will not be reflected with UPDATE query to database at the end of transaction
         * because dbBlogObject is not in PC, or we can say dbBlogObject is not in managed state,
         * to put it into PC or to make it managed state we have to call merge method explicitly
         */
        dbBlogObject.setContent("MY new content");
        /**
         * Here we're putting explicitLY detached object into PC so at the end of transaction if object is changed
         * then UPDATE query will be fired,
         * Note : When we are putting detached object into PC then at the end of transaction JPA needs to fire
         *        one select query to check whether this object is changed or not to take decision on UPDATE query
         *        fire or not?(This is called dirty checking)
         *
         */
        this.blogRepository.update(dbBlogObject);
    }
}
