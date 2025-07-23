package com.studentmgt.dao;

import com.studentmgt.model.Borrower;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BorrowerDao {
    public void saveBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(borrower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
