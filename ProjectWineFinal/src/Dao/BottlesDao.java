package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.BottlesEntity;
import tables.GrapeEntity;

import java.util.List;

public class BottlesDao {

    public void AddBottle(BottlesEntity bottlesEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(bottlesEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public int get187Bottles()
    {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<BottlesEntity> result = session.createQuery("from BottlesEntity").list();
            int sum = 0;
            for(int i=0;i<result.size();i++)
            {
                if(result.get(i).getBottletypeId() == 1) {
                    sum = sum + result.get(i).getBottlequantity();
                }
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public int get200Bottles()
    {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<BottlesEntity> result = session.createQuery("from BottlesEntity").list();
            int sum = 0;
            for(int i=0;i<result.size();i++)
            {
                if(result.get(i).getBottletypeId() == 2) {
                    sum = sum + result.get(i).getBottlequantity();
                }
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }


    public int get375Bottles()
    {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<BottlesEntity> result = session.createQuery("from BottlesEntity").list();
            int sum = 0;
            for(int i=0;i<result.size();i++)
            {
                if(result.get(i).getBottletypeId() == 3) {
                    sum = sum + result.get(i).getBottlequantity();
                }
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public int get750Bottles()
    {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<BottlesEntity> result = session.createQuery("from BottlesEntity").list();
            int sum = 0;
            for(int i=0;i<result.size();i++)
            {
                if(result.get(i).getBottletypeId() == 4) {
                    sum = sum + result.get(i).getBottlequantity();
                }
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }









    public int getBottlesMaxID() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from BottlesEntity").list().size();
        }
    }

    public List getBottles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from BottlesEntity").list();
        }
    }
}
