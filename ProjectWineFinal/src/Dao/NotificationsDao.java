package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.persister.entity.AbstractEntityPersister;
import tables.NotificationsEntity;
import tables.WinetypeEntity;

import java.util.List;

public class NotificationsDao {



    public void AddNotification(NotificationsEntity notificationsEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(notificationsEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void UpdateNotification(NotificationsEntity notificationsEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(notificationsEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }





    public void DeleteNotification(NotificationsEntity notificationsEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(notificationsEntity);
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
            return session.createQuery("from NotificationsEntity ").list().size();
        }
    }





    public List getNotifications() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from NotificationsEntity ").list();
        }
    }


}
