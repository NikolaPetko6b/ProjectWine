package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.PrivilegetypeEntity;

import java.util.List;

public class PrivilegeTypeDao {
    public void AddPrivilege(PrivilegetypeEntity privilegetypeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(privilegetypeEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List getPrivilegeType() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from PrivilegetypeEntity ").list();
        }
    }
}

