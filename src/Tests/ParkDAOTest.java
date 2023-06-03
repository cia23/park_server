package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.ParkDAO;
import Model.Park;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;


public class ParkDAOTest {
    private Database db;
    private Park park;
    private ParkDAO pDao;

    public ParkDAOTest(){ }

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        park = new Park("p1", "Yosemite", 35.9F, 140.1F, "CA");
        Connection conn = db.getConnection();
        db.clearTables();
        pDao = new ParkDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        this.db.closeConnection(false);
    }


    @Test
    public void insertPass() throws DataAccessException {
        this.pDao.insert(this.park);
        Park compareTest = this.pDao.find(this.park.getParkID());
        Assertions.assertNotNull(compareTest);
        Assertions.assertEquals(this.park, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        this.pDao.insert(this.park);
        Assertions.assertThrows(DataAccessException.class, () -> {
            this.pDao.insert(this.park);
        });
    }

    @Test
    public void findPass() throws DataAccessException {
        this.pDao.insert(this.park);
        Park newPark = new Park("p2", "Yellowstone", 40.2338F, 111.6585F, "WY");
        this.pDao.insert(newPark);
        Park compareTest = this.pDao.find(newPark.getParkID());
        Assertions.assertNotNull(compareTest);
        Assertions.assertEquals(newPark, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        this.pDao.insert(this.park);
        Park newPark = new Park("p2", "Yellowstone", 40.2338F, 111.6585F, "WY");
        Assertions.assertNull(this.pDao.find(newPark.getParkID()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        this.pDao.insert(this.park);
        Park newPark = new Park("p2", "Yellowstone", 40.2338F, 111.6585F, "WY");
        this.pDao.insert(newPark);
        ArrayList<Park> actualEvents = new ArrayList();
        actualEvents.add(this.park);
        actualEvents.add(newPark);
        ArrayList<Park> compareTest = this.pDao.findAll();
        Assertions.assertNotNull(compareTest);
        Assertions.assertEquals(actualEvents, compareTest);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        this.pDao.insert(this.park);
        Park newPark = new Park("p2", "Yellowstone", 40.2338F, 111.6585F, "WY");
        ArrayList<Park> actualEvents = new ArrayList();
        actualEvents.add(this.park);
        actualEvents.add(newPark);
        ArrayList<Park> compareTest = this.pDao.findAll();
        Assertions.assertNotNull(compareTest);
        Assertions.assertNotEquals(actualEvents, compareTest);
    }

    @Test
    public void deletePass() throws DataAccessException {
        this.pDao.insert(this.park);
        Park newPark = new Park("p2", "Yellowstone", 40.2338F, 111.6585F, "WY");
        this.pDao.insert(newPark);
        this.pDao.delete("p2");
        Park compareTest = this.pDao.find(newPark.getParkID());
        Assertions.assertNull(compareTest);
        compareTest = this.pDao.find(this.park.getParkID());
        Assertions.assertEquals(this.park, compareTest);
    }
}