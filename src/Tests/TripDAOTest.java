package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.TripDAO;
import Model.Trip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TripDAOTest {
    private Database db;
    private Trip trip;
    private TripDAO tDao;

    @BeforeEach
    public void setUp() throws DataAccessException
    {
        db = new Database();
        trip = new Trip("t1", "u1", "p1", "220101");
        Connection conn = db.getConnection();
        db.clearTables();
        tDao = new TripDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        this.tDao.insert(this.trip);
        Trip compareTest = tDao.find(this.trip.getTripID());
        assertNotNull(compareTest);
        assertEquals(this.trip, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        this.tDao.insert(this.trip);
        assertThrows(DataAccessException.class, ()-> this.tDao.insert(this.trip));
    }

    @Test
    public void findPass() throws DataAccessException {
        this.tDao.insert(this.trip);
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        this.tDao.insert(newTrip);
        Trip compareTest = this.tDao.find(newTrip.getTripID());
        assertNotNull(compareTest);
        assertEquals(newTrip, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        this.tDao.insert(this.trip);
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        assertNull(this.tDao.find(newTrip.getTripID()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        this.tDao.insert(this.trip);
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        this.tDao.insert(newTrip);
        ArrayList<Trip> actualTrips = new ArrayList<>();
        actualTrips.add(this.trip);
        actualTrips.add(newTrip);
        ArrayList<Trip> compareTest = this.tDao.findAll();
        assertNotNull(compareTest);
        assertEquals(actualTrips, compareTest);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        this.tDao.insert(this.trip);
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        ArrayList<Trip> actualTrips = new ArrayList<>();
        actualTrips.add(this.trip);
        actualTrips.add(newTrip);
        ArrayList<Trip> compareTest = this.tDao.findAll();
        assertNotNull(compareTest);
        assertNotEquals(actualTrips, compareTest);
    }

    @Test
    public void deletePass() throws DataAccessException {
        this.tDao.insert(this.trip);
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        this.tDao.insert(newTrip);
        this.tDao.delete("t2");
        Trip compareTest = this.tDao.find(newTrip.getTripID());
        assertNull(compareTest);
        compareTest = this.tDao.find(this.trip.getTripID());
        assertEquals(this.trip, compareTest);
    }

    @Test
    public void testClear() throws DataAccessException {
        tDao.insert(this.trip);
        tDao.clearTables();
        Trip compareTest = tDao.find(this.trip.getTripID());
        assertNull(compareTest);
    }
}