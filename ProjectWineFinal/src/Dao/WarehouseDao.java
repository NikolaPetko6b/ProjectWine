package Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.BottletypeEntity;
import tables.WarehouseEntity;

import java.util.List;

public class WarehouseDao {
    public void AddWarehouse(WarehouseEntity warehouseEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(warehouseEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }









    public List getWarehouses() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from WarehouseEntity ").list();
        }
    }
}