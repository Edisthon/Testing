package com.studentmgt.dao;

import com.studentmgt.model.Location;
import com.studentmgt.model.LocationType;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class LocationDao {
    public void saveLocation(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Location getLocation(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Location.class, id);
        }
    }

    public String getProvinceName(UUID villageId) {
        Location location = getLocation(villageId);
        while (location != null && location.getLocationType() != LocationType.PROVINCE) {
            location = location.getParent();
        }
        return location != null ? location.getLocationName() : null;
    }
}
