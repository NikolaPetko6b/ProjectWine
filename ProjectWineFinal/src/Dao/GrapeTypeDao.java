package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.BottletypeEntity;
import tables.GrapeEntity;
import tables.GrapetypeEntity;

import java.util.List;

public class GrapeTypeDao {

    public void AddGrapeType(GrapetypeEntity grapetypeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(grapetypeEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }









    public List getGrapeType() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from GrapetypeEntity ").list();
        }
    }
}
