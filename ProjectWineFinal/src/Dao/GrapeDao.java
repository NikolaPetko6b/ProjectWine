package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.GrapeEntity;

import java.util.List;

public class GrapeDao {
    public void AddGrape(GrapeEntity grapeEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(grapeEntity);
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
            return session.createQuery("from GrapeEntity").list().size();
        }
    }

    public double getWhiteGrape()
    {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<GrapeEntity> result = session.createQuery("from GrapeEntity").list();
            double sum = 0;
            for(int i=0;i<result.size();i++)
            {
                if(result.get(i).getGrapetypeId() == 1) {
                    sum = sum + result.get(i).getGrapequantity();
                }
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public double getRedGrape()
    {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<GrapeEntity> result = session.createQuery("from GrapeEntity").list();
            double sum = 0;
            for(int i=0;i<result.size();i++)
            {
                if(result.get(i).getGrapetypeId() == 2) {
                    sum = sum + result.get(i).getGrapequantity();
                }
            }
            return sum;
        }
        catch (Exception e)
        {
            return 0;
        }
    }



    public List getGrape() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from GrapeEntity").list();
        }
    }
}
