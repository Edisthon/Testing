package com.studentmgt.dao;

import com.studentmgt.model.Book;
import com.studentmgt.model.Borrower;
import com.studentmgt.model.User;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public void borrowBook(Book book, User user) {
        // Implementation depends on membership rules, etc.
        // For now, just create a record.
        Borrower borrower = new Borrower();
        borrower.setBook(book);
        // borrower.setUser(user); // Need to add user to Borrower entity
        borrower.setPickupDate(new Date());
        // Set due date based on membership type
        saveBorrower(borrower);
    }

    public long getLateFees(Borrower borrower) {
        if (borrower.getReturnDate() != null && borrower.getDueDate() != null) {
            long diffInMillies = Math.abs(borrower.getReturnDate().getTime() - borrower.getDueDate().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff > 0) {
                // Assuming a late fee of 1 per day
                return diff * 1;
            }
        }
        return 0;
    }
}
