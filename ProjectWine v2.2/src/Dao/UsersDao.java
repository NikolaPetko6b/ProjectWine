package Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tables.UsersEntity;

import java.io.IOException;
import java.util.List;

public class UsersDao {


    public static int loggedid;

    public  int getLoggedid() {
        return loggedid;
    }

    public  void setLoggedid(int loggedid) {
        UsersDao.loggedid = loggedid;
    }

    public void AddUser(UsersEntity usersEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(usersEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public void DeleteUser(UsersEntity usersEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(usersEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UsersEntity").list();
        }
    }

    public int getMaxID() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UsersEntity").list().size();
        }
    }




    public UsersEntity getUserByUsername(String Username) throws IOException{



        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (UsersEntity) session.createQuery(
                    "from UsersEntity person where username = :Username").setParameter("Username", Username).getSingleResult();

        }
        catch(Exception e)
        {

        }



        return (UsersEntity)null;
    }

}