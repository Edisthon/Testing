package com.studentmgt.dao;

import com.studentmgt.model.Membership;
import com.studentmgt.model.User;
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

    public boolean canBorrow(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Membership membership = session.createQuery("from Membership where reader = :user", Membership.class)
                    .setParameter("user", user)
                    .uniqueResult();
            if (membership != null) {
                return membership.getBorrowedBooks() < membership.getMembershipType().getMaxBooks();
            }
            return false;
        }
    }
}
