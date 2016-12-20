import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Set;

/**
 * Created by russl on 11/13/2016.
 */
public class Main {
//    private static final SessionFactory ourSessionFactory;
//    private static final ServiceRegistry serviceRegistry;

    static {
//        try {
//
//            serviceRegistry = new StandardServiceRegistryBuilder()
//                    .configure() // configures settings from hibernate.cfg.xml
//                    .build();
//
//            ourSessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
//
//
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
    }

//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }

    public static void scanTables() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        try {
            System.out.println("querying all the managed entities...");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Metamodel metamodel = emf.getMetamodel();
            Set<EntityType<?>> entities1 = metamodel.getEntities();


            entities1.forEach(e -> {
                System.out.println(e.getName());
                System.out.println("-->");

                javax.persistence.Query query = entityManager.createQuery("from  " + e.getName());
                Set<Attribute<?, ?>> attributes = (Set<Attribute<?, ?>>) e.getAttributes();


try {
    query.getResultList().forEach(r -> {
        String s = gson.toJson(r);
        System.out.println("  > " + s);
    });
} catch ( PersistenceException ex) {
    System.out.println(" e:  " + ex.getLocalizedMessage());
}
                System.out.println("<--");
            });
            System.out.println(" -----------------------------------------------------------------");
        } finally {
            entityManager.close();

            emf.close();
        }
    }
    public static void listTables() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager entityManager = emf.createEntityManager();

        try {
            System.out.println("querying all the managed entities...");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Metamodel metamodel = emf.getMetamodel();
            Set<EntityType<?>> entities1 = metamodel.getEntities();


            entities1.forEach(e -> {
                System.out.println(e.getName());
                System.out.println("-->");

                javax.persistence.Query query = entityManager.createQuery("from  " + e.getName());
                Set<Attribute<?, ?>> attributes = (Set<Attribute<?, ?>>) e.getAttributes();

                attributes.forEach(attribute -> {System.out.print(attribute.getName() + "  ");});
                System.out.println("");

                System.out.println("<--");
            });
            System.out.println(" -----------------------------------------------------------------");
        } finally {
            entityManager.close();

            emf.close();
        }
    }
//public static void createVideoItem() {
//    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//
//    VideoItemDao scottDao = context.getBean(VideoItemDao.class);
//
//
//    VideoItemEntity videoItem = new VideoItemEntity();
//    videoItem.setCreatedBy("russl");
//    videoItem.setCreateDt( LocalDateTime.now());
//    videoItem.setTitle("No, Please!");
//
//    videoItem.setInUseStartDt( LocalDateTime.now());
//    videoItem.setInUseCd("aaa");
//
//
//    scottDao.persist(videoItem);
//
//
//
//System.out.println(videoItem);
//}
    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            Set<EntityType<?>> entities = ourSessionFactory.getMetamodel().getEntities();
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//            entities.forEach(e -> {
//                System.out.println(e.getName());
//                System.out.println("-->");
//
//                org.hibernate.query.Query query = session.createQuery("from " + e.getName());
//                System.out.println("executing: " + query.getQueryString());
//                query.list().forEach(r -> {
//                    String s = gson.toJson(r);
//                    System.out.println("  > " + s);
//                });
//
//
//                System.out.println("<--");
//            });
//
//        } finally {
//            session.close();
//        }

//        scanTables();
//        createVideoItem();

        System.out.println(" complete ");
        System.exit(0);
    }
}