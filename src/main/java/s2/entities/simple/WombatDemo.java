package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class WombatDemo {
//    public static void f0() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
//        EntityManager entityManager = emf.createEntityManager();
//
////        javax.persistence.Query q1 = entityManager.createQuery("from VCourseEntity");
////
////        q1.getResultList().forEach(e -> {
////            entityManager.getTransaction().begin();
////            entityManager.remove(e);
////            entityManager.getTransaction().commit();
////        });
//
//        Query q2 = entityManager.createQuery("from VideoItemEntity ");
//
//        q2.getResultList().forEach(e -> {
//            entityManager.getTransaction().begin();
//            entityManager.remove(e);
//            entityManager.getTransaction().commit();
//        });
//
////        javax.persistence.Query q3 = entityManager.createQuery("from VCourseEntity");
////
////        q3.getResultList().forEach(e -> {
////            entityManager.getTransaction().begin();
////            entityManager.remove(e);
////            entityManager.getTransaction().commit();
////        });
//
//        System.out.println(" done ");
//
//    }

    public static void f1() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();


        WombatEntity v2 = new WombatEntity();
        VideoGroupKey myKey = new VideoGroupKey();
        myKey.setGroupId("78d79c85-610b-45cd-9971-a1504023bb97");
        myKey.setItemId("4d71388e-dc93-4c4f-9423-b58a073044c0");
        v2.setName("NY");
        v2.setMyKey(myKey);

        entityManager.getTransaction().begin();
        entityManager.persist(v2);
        entityManager.getTransaction().commit();
//        entityManager.getTransaction().begin();
//        entityManager.persist(student2);
//        entityManager.getTransaction().commit();


        System.out.println(" done ");


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
//        show();
        System.exit(0);

    }
}
