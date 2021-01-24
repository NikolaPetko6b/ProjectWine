package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.PrivilegetypeEntity;
import tables.UsersEntity;
import tables.WinetypeEntity;

import java.io.IOException;
import java.util.List;

public class WineTypeDao {
    public void AddWineType(WinetypeEntity winetypeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(winetypeEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void UpdateWineType(WinetypeEntity winetypeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(winetypeEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void DeleteWineType(WinetypeEntity winetypeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(winetypeEntity);
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
            return session.createQuery("from WinetypeEntity").list().size();
        }
    }


    public WinetypeEntity getWineTypeByUserID(int id)  {



        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (WinetypeEntity) session.createQuery(
                    "from WinetypeEntity person where userId = :id").setParameter("id", id).getSingleResult();

        }
        catch(Exception e)
        {

        }


        return null;

    }


    public List getWineType() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from WinetypeEntity ").list();
        }
    }
}

