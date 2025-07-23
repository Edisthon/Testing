package com.studentmgt.dao;

import com.studentmgt.model.Shelf;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShelfDao {
    public void saveShelf(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(shelf);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
