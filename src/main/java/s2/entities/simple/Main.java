package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void f0() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

//        javax.persistence.Query q1 = entityManager.createQuery("from VCourseEntity");
//
//        q1.getResultList().forEach(e -> {
//            entityManager.getTransaction().begin();
//            entityManager.remove(e);
//            entityManager.getTransaction().commit();
//        });

        javax.persistence.Query q2 = entityManager.createQuery("from VStudentEntity ");

        q2.getResultList().forEach(e -> {
            entityManager.getTransaction().begin();
            entityManager.remove(e);
            entityManager.getTransaction().commit();
        });

//        javax.persistence.Query q3 = entityManager.createQuery("from VCourseEntity");
//
//        q3.getResultList().forEach(e -> {
//            entityManager.getTransaction().begin();
//            entityManager.remove(e);
//            entityManager.getTransaction().commit();
//        });

        System.out.println(" done ");

    }

    public static void f1() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        Set<VCourseEntity> courses = new HashSet<>();


        VCourseEntity c1 = new VCourseEntity("PG");
        c1.setCourseDesc("Parental Guidance advised");
        c1.setCourseTip("parent guidance");


        VCourseEntity c2 = new VCourseEntity("R");
        c2.setCourseTip("restricted");
        c2.setCourseDesc("Restricted");

        courses.add(c1);
        courses.add(c2);

        VStudentEntity student1 = new VStudentEntity("x1", courses);
        VStudentEntity student2 = new VStudentEntity("x2", courses);


        entityManager.getTransaction().begin();
        entityManager.persist(student1);
        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        entityManager.persist(student2);
        entityManager.getTransaction().commit();


        System.out.println(" done ");


    }

    public static void f2() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        Set<VCourseEntity> courses = new HashSet<>();


        VCourseEntity wwww = new VCourseEntity("wwww");


//        CourseEntity courseEntity = new CourseEntity("Computer Science");

//        courses.add(maths);
//        courses.add(courseEntity);
//
//        StudentEntity student1 = new StudentEntity("Eswar", courses);
//        StudentEntity student2 = new StudentEntity("Joe", courses);
//

        entityManager.getTransaction().begin();
        entityManager.persist(wwww);
        entityManager.getTransaction().commit();


//        entityManager.getTransaction().begin();
//        entityManager.persist(student2);
//        entityManager.getTransaction().commit();


        System.out.println(" done ");


    }

    public static void f3() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        String key = "bcc2f804-e537-49d5-898b-520d3a320d60";

//    entityManager.getTransaction().begin();
        VCourseEntity courseEntity = entityManager.find(VCourseEntity.class, key);
//    entityManager.getTransaction().commit();

        System.out.println("->" + courseEntity + "<=");

    }

    public static void f4() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        String key = "bcc2f804-e537-49d5-898b-520d3a320d60";

        VCourseEntity courseEntity = entityManager.find(VCourseEntity.class, key);

        System.out.println("->" + courseEntity + "<=");


        Set<VCourseEntity> courses = new HashSet<>();


        courses.add(courseEntity);

        VStudentEntity student1 = new VStudentEntity("swan", courses);


        entityManager.getTransaction().begin();
        entityManager.persist(student1);
        entityManager.getTransaction().commit();

    }

    public static void show() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        javax.persistence.Query q1 = entityManager.createQuery("from VStudentEntity ");

        q1.getResultList().forEach(e -> {
            System.out.println("-->" + e);
        });


    }

    public static void main(String[] args) {
//sec();
        f1();
//        f0();
        show();
        System.exit(0);

    }
}
