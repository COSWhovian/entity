package s2.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Created by russl on 11/27/2016.
 */


public abstract class AbstractEntityDao<E> {

    static EntityManagerFactory entityManagerFactory;

    public AbstractEntityDao() {
        // default constructor
    }

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("manager1");
    }


    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }


    public void persist(E entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void update(E entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }


    public abstract E findById(String id);

    public void delete(E entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }


    public abstract String getQueryString();

    public List<E> findAll() {
        EntityManager entityManager = getEntityManager();
        javax.persistence.Query query = entityManager.createQuery(getQueryString());

       return (List<E>) query.getResultList();


    }

    public void deleteAll() {
        List<E> entities = findAll();

        entities.forEach(entity -> {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        });


    }


}
