package Dao;

import tables.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    public static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/projectwinedb");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "123456789");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.addAnnotatedClass(tables.BottlesEntity.class);
                configuration.addAnnotatedClass(tables.BottletypeEntity.class);
                configuration.addAnnotatedClass(tables.GrapeEntity.class);
                configuration.addAnnotatedClass(tables.GrapetypeEntity.class);
                configuration.addAnnotatedClass(tables.PrivilegetypeEntity.class);
                configuration.addAnnotatedClass(tables.UsersEntity.class);

                configuration.addAnnotatedClass(tables.WineEntity.class);
                configuration.addAnnotatedClass(tables.WinetypeEntity.class);
                configuration.addAnnotatedClass(tables.CurrentStockEntity.class);
                configuration.setProperties(settings);

                //configuration.addAnnotatedClass(Client.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

