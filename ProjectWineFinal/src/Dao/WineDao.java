package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.WineEntity;

import java.util.List;

public class WineDao {
    public void AddWine(WineEntity wineEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(wineEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }





    public int getMaxID() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from WineEntity").list().size();
        }
    }

    public List getWine() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from WineEntity ").list();
        }
    }
}
