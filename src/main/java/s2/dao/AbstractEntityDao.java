package s2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import s2.project.ProjectTypeEntity;

import java.util.List;

/**
 * Created by russl on 11/27/2016.
 */


public abstract class AbstractEntityDao<E> {

    private Session currentSession;

    private Transaction currentTransaction;

    public AbstractEntityDao() {

    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        SessionFactory
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(E entity) {
        getCurrentSession().save(entity);
    }


    public void update(E entity) {
        getCurrentSession().update(entity);
    }


    abstract public E findById(String id);

    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }


    abstract public String getQueryString();

    public List<E> findAll() {
        List<E> entities = (List<E>) getCurrentSession().createQuery(getQueryString()).list();
        return entities;
    }

    public void deleteAll() {
        List<E> entities = findAll();
        entities.forEach(e -> delete(e));
    }
}
