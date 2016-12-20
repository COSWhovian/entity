package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import org.hibernate.exception.ConstraintViolationException;
import s2.entities.video.VideoItemEntity;
import s2.entities.video.VideoRatingEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BookPubDemo {


    public static void f1() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();



        entityManager.getTransaction().begin();
         Book bookA = new Book("Book A");

        Publisher publisherA = new Publisher("Publisher A");

        BookPublisher bookPublisher = new BookPublisher();
        bookPublisher.setBook(bookA);
        bookPublisher.setPublisher(publisherA);
        bookPublisher.setPublishedDate(new Date());
        bookA.getBookPublishers().add(bookPublisher);


        entityManager.persist(publisherA);
        entityManager.persist(bookA);


        entityManager.getTransaction().commit();


        System.out.println(" done ");


    }


    public static void f2() {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();



        entityManager.getTransaction().begin();
        Book bookA = new Book("Book AB");

        Publisher publisherA = new Publisher("Publisher AB");

        BookPublisher bookPublisher = new BookPublisher();
        bookPublisher.setBook(bookA);
        bookPublisher.setPublisher(publisherA);
        bookPublisher.setPublishedDate(new Date());
        bookA.getBookPublishers().add(bookPublisher);


        entityManager.persist(publisherA);
        entityManager.persist(bookA);


        entityManager.getTransaction().commit();


        System.out.println(" done ");
        System.out.println(" done ");


    }


    public static void show() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        javax.persistence.Query q1 = entityManager.createQuery("from Book ");

        q1.getResultList().forEach(e -> {
            System.out.println("-->" + e);
        });


    }

    public static void main(String[] args) {

        f2();
        show();
         System.exit(0);

    }
}
