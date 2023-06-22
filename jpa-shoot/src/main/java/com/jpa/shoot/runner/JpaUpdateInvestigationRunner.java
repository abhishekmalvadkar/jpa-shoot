package com.jpa.shoot.runner;

import com.jpa.shoot.entity.BlogEntity;
import com.jpa.shoot.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    /**
     * Due to @@Transactional annotation on method level PC will start from method start
     * amd will end at method end
     * PC state : []
     */
    public void run(String... args) throws Exception {
    }

    @Transactional
    public void updateCaseWithManagedEntityObjectWithTransactionalAnnotationOnCurrentMethod() {
        Integer blogId = 4;

        /**
         * This line will fetch blog and put into PC
         * PC state : [dbBlogObject]
         */
        BlogEntity dbBlogObject = this.em.find(BlogEntity.class, blogId);

        /**
         * This line will change the managed blog entity
         * PC state : [dbBlogObject++] ++ means changed
         */
        dbBlogObject.setContent("MY new content V2");

        /**
         * Here as we know that dbBlogObject is managed, so we don't need to use
         * merge method explicitly( If you want , you can use, but it does not maje any sense when your entity
         * object is managed, it will make sense only when your entity object is detached) because when transaction ends then hibernate will
         * check in the PC that any object value is modified or not ( this type of dirty checking is called PC based dirty checking) and
         * if and only if it finds that entity any entity object value is changed then only hibernate will fire UPDATE query otherwise
         * it will not fire UPDATE query, you can see logs
         */

        /**
         * Conclusions on update
         * - If you have detached object then mandatory you have to call merge method to put it into PC and in this case at the end of transaction
         *   SQL based dirty checking will be done where hibernate will fire one select query to check whether this object has
         *   been changed or not? to take UPDATE query decision
         * - if you have managed object then using merge method is optional because your object is already available in PC,
         *   and in this case at the end of transaction PC based dirty checking will be happened where hibernate checks from PC that this object
         *   is changed or not from its previous state ? to take UPDATE query decision , in this case no extra SQL select query will be executed
         */
    }

    public void updateCaseWithDetachedObjectWithoutTransactionAnnotationOnCurrentMethod() {
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

        /**
         * Conclusions
         * - If you add detached object into PC then hibernate needs to check whether this object is changed
         *   or not to take decision on UPDATE query execution and hibernate is checking this by firing one select
         *   query to database , you can see in log and this type of dirty checking is called SQL query based dirty checking
         */
    }
}
