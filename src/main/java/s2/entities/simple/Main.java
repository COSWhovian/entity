package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import org.hibernate.exception.ConstraintViolationException;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


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

        javax.persistence.Query q2 = entityManager.createQuery("from VideoItemEntity ");

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


        Set<VideoRatingEntity> courses = new HashSet<>();


//        VideoRatingEntity c1 = new VideoRatingEntity("PG");
//        c1.setCourseDesc("Parental Guidance advised");
//        c1.setCourseTip("parent guidance");
//
//
//        VideoRatingEntity c2 = new VideoRatingEntity("R");
//        c2.setCourseTip("restricted");
//        c2.setCourseDesc("Restricted");
//

//        VideoRatingEntity c1 = entityManager.find(VideoRatingEntity.class, "5e0f9732-31eb-4967-a82d-32312a9b29e8");
//        VideoRatingEntity c2 = entityManager.find(VideoRatingEntity.class, "11e2e3cb-2060-448a-9536-601844ab474d");

        Query query = entityManager.createQuery("from VideoRatingEntity vr where vr.ratingName= :ratingCd");
        query.setParameter("ratingCd", "G");
        VideoRatingEntity c1 = (VideoRatingEntity) query.getSingleResult();

        Query query2 = entityManager.createQuery("from VideoRatingEntity vr where vr.ratingName= :ratingCd");
        query2.setParameter("ratingCd", "UR");
        VideoRatingEntity c2 = (VideoRatingEntity) query.getSingleResult();

        courses.add(c1);
        courses.add(c2);

        VideoItemEntity student1 = new VideoItemEntity("x1", courses);
        VideoItemEntity student2 = new VideoItemEntity("x2", courses);


        entityManager.getTransaction().begin();
        entityManager.persist(student1);
        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        entityManager.persist(student2);
        entityManager.getTransaction().commit();


        System.out.println(" done ");


    }

    public static VideoRatingEntity getRatedG() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("G");
        vCourseEntity.setCourseTip("rated G");
        vCourseEntity.setCourseDesc("General Audiences");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedPG() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("PG");
        vCourseEntity.setCourseTip("rated PG");
        vCourseEntity.setCourseDesc("Parental Guidance Suggested");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedPG13() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("PG-13");
        vCourseEntity.setCourseTip("rated PG-13");
        vCourseEntity.setCourseDesc("Parents Strongly Cautioned");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedR() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("R");
        vCourseEntity.setCourseTip("rated R");
        vCourseEntity.setCourseDesc("Restricted");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedNC17() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("NC-17");
        vCourseEntity.setCourseTip("rated NC-17");
        vCourseEntity.setCourseDesc("Adults Only");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedX() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("X");
        vCourseEntity.setCourseTip("rated X");
        vCourseEntity.setCourseDesc("Persons Under 16 Not Admitted");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedNR() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("NR");
        vCourseEntity.setCourseTip("rated NR");
        vCourseEntity.setCourseDesc("Not Rated");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedUR() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("UR");
        vCourseEntity.setCourseTip("unrated");
        vCourseEntity.setCourseDesc("Unrated");

        return vCourseEntity;
    }

    public static void f2() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        Set<VideoRatingEntity> ratingsList = new HashSet<>();

        ratingsList.add(getRatedG());
        ratingsList.add(getRatedPG());
        ratingsList.add(getRatedPG13());
        ratingsList.add(getRatedR());
        ratingsList.add(getRatedNC17());
        ratingsList.add(getRatedX());
        ratingsList.add(getRatedNR());
        ratingsList.add(getRatedUR());


//
        ratingsList.forEach(e -> {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(e);
                entityManager.getTransaction().commit();

            } catch (ConstraintViolationException ex) {
                System.out.println(" --> rating already exists:  " + e.getRatingName());


            } catch (Exception ex) {
//    System.out.println( " *** Exception:  " + ex.getLocalizedMessage());
                System.out.println(" --> rating already exists:  " + e.getRatingName());


            }

        });


        System.out.println(" done ");


    }

    public static void f3() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        String key = "bcc2f804-e537-49d5-898b-520d3a320d60";

//    entityManager.getTransaction().begin();
        VideoRatingEntity courseEntity = entityManager.find(VideoRatingEntity.class, key);
//    entityManager.getTransaction().commit();

        System.out.println("->" + courseEntity + "<=");

    }

    public static void f4() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        String key = "bcc2f804-e537-49d5-898b-520d3a320d60";

        VideoRatingEntity courseEntity = entityManager.find(VideoRatingEntity.class, key);

        System.out.println("->" + courseEntity + "<=");


        Set<VideoRatingEntity> courses = new HashSet<>();


        courses.add(courseEntity);

        VideoItemEntity student1 = new VideoItemEntity("swan", courses);


        entityManager.getTransaction().begin();
        entityManager.persist(student1);
        entityManager.getTransaction().commit();

    }

    public static void show() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        javax.persistence.Query q1 = entityManager.createQuery("from VideoItemEntity ");

        q1.getResultList().forEach(e -> {
            System.out.println("-->" + e);
        });


    }

    public static void main(String[] args) {
//sec();
//        f2();
        f1();
        show();
        System.exit(0);

    }
}
