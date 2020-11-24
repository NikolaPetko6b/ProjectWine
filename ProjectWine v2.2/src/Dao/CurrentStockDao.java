package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.CurrentStockEntity;
import tables.UsersEntity;

import java.io.IOException;
import java.util.List;

public class CurrentStockDao {
    public void AddRecord(CurrentStockEntity currentStockEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(currentStockEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public void DeleteRecord(CurrentStockEntity currentStockEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(currentStockEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List getRecords() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from CurrentStockEntity ").list();
        }
    }

    public int getLatestRecordID() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from CurrentStockEntity").list().size();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }




    public CurrentStockEntity getRecordByID(int id) throws IOException{



        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (CurrentStockEntity) session.createQuery(
                    "from CurrentStockEntity record where currentstockid = :id").setParameter("id", id).getSingleResult();

        }
        catch(Exception e)
        {

        }



        return (CurrentStockEntity) null;
    }


    public double curWhiteGrape() throws IOException
    {

        try
        {
            CurrentStockEntity latest = this.getRecordByID(this.getLatestRecordID());
            return latest.getWhiteGrape();
        }catch (Exception e)
        {

        }
        return 0;

    }

    public double curRedGrape() throws IOException
    {

        try
        {
            CurrentStockEntity latest = this.getRecordByID(this.getLatestRecordID());
            return latest.getRedGrape();
        }catch (Exception e)
        {

        }

        return 0;
    }


    public int cur187Bottles() throws IOException
    {

        try
        {
            CurrentStockEntity latest = this.getRecordByID(this.getLatestRecordID());
            return latest.getBottles187();
        }catch (Exception e)
        {

        }
        return 0;

    }

    public int cur200Bottles() throws IOException
    {

        try
        {
            CurrentStockEntity latest = this.getRecordByID(this.getLatestRecordID());
            return latest.getBottles200();
        }catch (Exception e)
        {

        }
        return 0;

    }

    public int cur375Bottles() throws IOException
    {

        try
        {
            CurrentStockEntity latest = this.getRecordByID(this.getLatestRecordID());
            return latest.getBottles375();
        }catch (Exception e)
        {

        }
        return 0;

    }


    public int cur750Bottles() throws IOException
    {

        try
        {
            CurrentStockEntity latest = this.getRecordByID(this.getLatestRecordID());
            return latest.getBottles750();
        }catch (Exception e)
        {

        }
        return 0;

    }






}




