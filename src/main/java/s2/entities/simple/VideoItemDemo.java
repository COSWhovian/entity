package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import org.hibernate.exception.ConstraintViolationException;
import s2.entities.video.VideoItemEntity;
import s2.entities.video.VideoRatingEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class VideoItemDemo {
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


        Query query = entityManager.createQuery("from VideoRatingEntity vr where vr.ratingName= :ratingCd");
        query.setParameter("ratingCd", "G");
        VideoRatingEntity c1 = (VideoRatingEntity) query.getSingleResult();

        Query query2 = entityManager.createQuery("from VideoRatingEntity vr where vr.ratingName= :ratingCd");
        query2.setParameter("ratingCd", "UR");
        VideoRatingEntity c2 = (VideoRatingEntity) query2.getSingleResult();

        courses.add(c1);
        courses.add(c2);

        VideoItemEntity v1 = new VideoItemEntity("Escape from New York", courses);
        VideoItemEntity v2 = new VideoItemEntity("Escape from L.A.", courses);
//

        entityManager.getTransaction().begin();
        entityManager.persist(v1);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.persist(v2);
        entityManager.getTransaction().commit();
//        entityManager.getTransaction().begin();
//        entityManager.persist(student2);
//        entityManager.getTransaction().commit();


        System.out.println(" done ");


    }

    public static VideoRatingEntity getRatedG() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("G");
        vCourseEntity.setRatingTip("rated G");
        vCourseEntity.setRatingDesc("General Audiences");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedPG() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("PG");
        vCourseEntity.setRatingTip("rated PG");
        vCourseEntity.setRatingDesc("Parental Guidance Suggested");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedPG13() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("PG-13");
        vCourseEntity.setRatingTip("rated PG-13");
        vCourseEntity.setRatingDesc("Parents Strongly Cautioned");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedR() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("R");
        vCourseEntity.setRatingTip("rated R");
        vCourseEntity.setRatingDesc("Restricted");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedNC17() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("NC-17");
        vCourseEntity.setRatingTip("rated NC-17");
        vCourseEntity.setRatingDesc("Adults Only");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedX() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("X");
        vCourseEntity.setRatingTip("rated X");
        vCourseEntity.setRatingDesc("Persons Under 16 Not Admitted");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedNR() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("NR");
        vCourseEntity.setRatingTip("rated NR");
        vCourseEntity.setRatingDesc("Not Rated");

        return vCourseEntity;
    }

    public static VideoRatingEntity getRatedUR() {
        VideoRatingEntity vCourseEntity = new VideoRatingEntity();
        vCourseEntity.setRatingName("UR");
        vCourseEntity.setRatingTip("unrated");
        vCourseEntity.setRatingDesc("Unrated");

        return vCourseEntity;
    }

    public static void f2() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        Query query = entityManager.createQuery("from VideoRatingEntity");
        List<VideoRatingEntity> resultList = query.getResultList();



        Set<VideoRatingEntity> ratingsList = new HashSet<>();

        ratingsList.add(getRatedG());
        ratingsList.add(getRatedPG());
        ratingsList.add(getRatedPG13());
        ratingsList.add(getRatedR());
        ratingsList.add(getRatedNC17());
        ratingsList.add(getRatedX());
        ratingsList.add(getRatedNR());
        ratingsList.add(getRatedUR());

        List<VideoRatingEntity> collect = ratingsList.stream().filter(x -> !resultList.stream().filter(r -> r
                .getRatingName().equals(x.getRatingName()
        )).findFirst().isPresent()).collect(Collectors.toList());

        collect.forEach( c -> System.out.println(" need to create:  " + c ));


        collect.forEach(e -> {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(e);
                entityManager.getTransaction().commit();

            } catch (ConstraintViolationException ex) {
                System.out.println(" --> rating already exists:  " + e.getRatingName());


            } catch (Exception ex) {
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
