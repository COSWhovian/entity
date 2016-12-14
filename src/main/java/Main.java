import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.metamodel.EntityType;
import java.util.Map;
import java.util.Set;

/**
 * Created by russl on 11/13/2016.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();

            ourSessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();


        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            Set<EntityType<?>> entities = ourSessionFactory.getMetamodel().getEntities();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            entities.forEach(e -> {
                System.out.println(e.getName());
                System.out.println("-->");

                org.hibernate.query.Query query = session.createQuery("from " + e.getName());
                System.out.println("executing: " + query.getQueryString());
                query.list().forEach(r -> {
                    String s = gson.toJson(r);
                    System.out.println("  > " + s);
                });


                System.out.println("<--");
            });

        } finally {
            session.close();
        }
    }
}