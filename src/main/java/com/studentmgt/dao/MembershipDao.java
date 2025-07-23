package com.studentmgt.dao;

import com.studentmgt.model.Membership;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MembershipDao {
    public void saveMembership(Membership membership) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
