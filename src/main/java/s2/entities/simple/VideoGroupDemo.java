package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import s2.entities.video.VideoGroupEntity;
import s2.entities.video.VideoItemEntity;
import s2.entities.video.VideoRatingEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;


public class VideoGroupDemo {


    public static void f1() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        Set<VideoItemEntity> items = new HashSet<>();


        Query query = entityManager.createQuery("from VideoItemEntity vr where vr.title= :title");
        query.setParameter("title", "Escape from New York c");
        VideoItemEntity c1 = (VideoItemEntity) query.getSingleResult();

        Query query2 = entityManager.createQuery("from VideoItemEntity vr where vr.title= :title");
        query2.setParameter("title", "Escape from L.A. c");
        VideoItemEntity c2 = (VideoItemEntity) query2.getSingleResult();

        items.add(c1);
        items.add(c2);

        VideoGroupEntity videoGroupEntity = new VideoGroupEntity();
        videoGroupEntity.setGroupDesc("The 'Escape from ...' series");
        videoGroupEntity.setTitle("Escape from ...");
        videoGroupEntity.setVideoItemEntities(items);


        entityManager.getTransaction().begin();
        entityManager.persist(videoGroupEntity);
        entityManager.getTransaction().commit();


        System.out.println(" done ");


    }


    public static void f2() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        String key = "78d79c85-610b-45cd-9971-a1504023bb97";

//    entityManager.getTransaction().begin();
        VideoGroupEntity courseEntity = entityManager.find(VideoGroupEntity.class, key);
//    entityManager.getTransaction().commit();

        System.out.println("->" + courseEntity + "<=");

    }

//    public static void f4() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//        EntityManager entityManager = emf.createEntityManager();
//
//
//        String key = "bcc2f804-e537-49d5-898b-520d3a320d60";
//
//        VideoRatingEntity courseEntity = entityManager.find(VideoRatingEntity.class, key);
//
//        System.out.println("->" + courseEntity + "<=");
//
//
//        Set<VideoRatingEntity> courses = new HashSet<>();
//
//
//        courses.add(courseEntity);
//
//        VideoItemEntity student1 = new VideoItemEntity("swan", courses);
//
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(student1);
//        entityManager.getTransaction().commit();
//
//    }

    public static void show() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        Query q1 = entityManager.createQuery("from VideoGroupEntity ");

        q1.getResultList().forEach(e -> {
VideoGroupEntity v = (VideoGroupEntity)e;
            System.out.println("-->" + v.getId());
            System.out.println("   " + v.getTitle());
            System.out.println("   " + v.getGroupDesc());

            Set<VideoItemEntity> videoItemEntities = v.getVideoItemEntities();
            videoItemEntities.forEach( i -> {
                System.out.println("----->" + i.getVideoId());
                System.out.println("      " + i.getTitle());
                Set<VideoRatingEntity> ratings = i.getRatings();
                ratings.forEach(r -> {
                    System.out.println("-------->" + r.getRatingId());
                    System.out.println("         " + r.getRatingName());
                    System.out.println("         " + r.getRatingDesc());
                    System.out.println("         " + r.getRatingTip());
                });

            });
            Set<WombatEntity> wombats = v.getWombats();
            wombats.forEach(w -> {
                System.out.println("      w->" + w.getMyKey().getGroupId());
                System.out.println("       ->" + w.getMyKey().getItemId());
                System.out.println("       ->" + w.getName());
            });
        });


    }

    public static void main(String[] args) {
//sec();
//        f2();
//        f1();
        show();
        System.exit(0);

    }
}
