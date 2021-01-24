package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.BottlesEntity;
import tables.BottletypeEntity;

import java.util.List;

public class BottleTypeDao {

    public void AddBottleType(BottletypeEntity bottletypeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(bottletypeEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }









    public List getBottleType() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from BottletypeEntity ").list();
        }
    }
}
