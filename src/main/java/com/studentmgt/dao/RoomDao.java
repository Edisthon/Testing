package com.studentmgt.dao;

import com.studentmgt.model.Room;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    public long getBookCount(Room room) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "select count(b.id) from Book b where b.shelf.room = :room", Long.class);
            query.setParameter("room", room);
            return query.getSingleResult();
        }
    }

    public Room getRoomWithFewestBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Room> query = session.createQuery(
                    "select r from Room r left join r.shelves s left join s.books b group by r order by count(b) asc", Room.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }
}
