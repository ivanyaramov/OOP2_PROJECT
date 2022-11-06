package sample.DBService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DatabaseService {

    StandardServiceRegistry registry;

    public DatabaseService() {
        initializeRegistry();
    }

    public void saveObject(Object object){
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            session.close();
            factory.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
            initializeRegistry();
        }
    }
    private void initializeRegistry(){
        registry = new StandardServiceRegistryBuilder()
        .configure()
        .build();
    }

    public Object getObjectByQuery(String hql){
        SessionFactory factory = buildFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        Object obj = query.getSingleResult();
        t.commit();
        session.close();
        return obj;
    }

    public Object getListOfObjectsByQuery(String hql){
        SessionFactory factory = buildFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        Object obj = query.list();
        t.commit();
        session.close();
        return obj;
    }

    private SessionFactory buildFactory(){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        return cfg.buildSessionFactory();
    }

}
