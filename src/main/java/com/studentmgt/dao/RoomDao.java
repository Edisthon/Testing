package com.studentmgt.dao;

import com.studentmgt.model.Room;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoomDao {
    public void saveRoom(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
