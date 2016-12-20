package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import org.hibernate.exception.ConstraintViolationException;
import s2.entities.video.VideoGroupEntity;
import s2.entities.video.VideoItemEntity;
import s2.entities.video.VideoRatingEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class VideoGroupDemo {


    public static void f1() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        Set<VideoItemEntity> courses = new HashSet<>();


        Query query = entityManager.createQuery("from VideoItemEntity vr where vr.title= :title");
        query.setParameter("title", "Escape from New York");
        VideoItemEntity c1 = (VideoItemEntity) query.getSingleResult();

        Query query2 = entityManager.createQuery("from VideoItemEntity vr where vr.title= :title");
        query2.setParameter("title", "Escape from L.A.");
        VideoItemEntity c2 = (VideoItemEntity) query2.getSingleResult();

        courses.add(c1);
        courses.add(c2);

        VideoGroupEntity videoGroupEntity = new VideoGroupEntity();
        videoGroupEntity.setGroup_desc("The 'Escape from ...' series");
        videoGroupEntity.setTitle("Escape from ...");
        videoGroupEntity.setVideoItemEntities(courses);
//        VideoItemEntity student2 = new VideoItemEntity("x2", courses);


        entityManager.getTransaction().begin();
        entityManager.persist(videoGroupEntity);
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

        Query q1 = entityManager.createQuery("from VideoItemEntity ");

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
