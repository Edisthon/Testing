package com.studentmgt.dao;

import com.studentmgt.model.Location;
import com.studentmgt.model.LocationType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationDaoTest {

    @Test
    public void testGetProvinceName() {
        LocationDao locationDao = new LocationDao();

        Location province = new Location();
        province.setLocationId(UUID.randomUUID());
        province.setLocationName("Kigali");
        province.setLocationType(LocationType.PROVINCE);

        Location district = new Location();
        district.setLocationId(UUID.randomUUID());
        district.setParent(province);
        district.setLocationType(LocationType.DISTRICT);

        Location sector = new Location();
        sector.setLocationId(UUID.randomUUID());
        sector.setParent(district);
        sector.setLocationType(LocationType.SECTOR);

        Location cell = new Location();
        cell.setLocationId(UUID.randomUUID());
        cell.setParent(sector);
        cell.setLocationType(LocationType.CELL);

        Location village = new Location();
        village.setLocationId(UUID.randomUUID());
        village.setParent(cell);
        village.setLocationType(LocationType.VILLAGE);

        locationDao.saveLocation(province);
        locationDao.saveLocation(district);
        locationDao.saveLocation(sector);
        locationDao.saveLocation(cell);
        locationDao.saveLocation(village);

        locationDao.saveLocation(province);
        locationDao.saveLocation(district);
        locationDao.saveLocation(sector);
        locationDao.saveLocation(cell);
        locationDao.saveLocation(village);
        assertEquals("Kigali", new LocationDao().getProvinceName(village.getLocationId()));
    }
}
