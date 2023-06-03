package DataAccess;

import Model.Trip;

import java.sql.*;
import java.util.ArrayList;

public class TripDAO {
    private final Connection conn;

    public TripDAO(Connection conn)
    {
        this.conn = conn;
    }


    public void insert(Trip trip) throws DataAccessException {
        String sql = "INSERT INTO trips (tripID, userID, parkID, date) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trip.getTripID());
            stmt.setString(2, trip.getUserID());
            stmt.setString(3, trip.getParkID());
            stmt.setString(4, trip.getDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    public Trip find(String tripID) throws DataAccessException {
        Trip trip;
        ResultSet rs = null;
        String sql = "SELECT * FROM trips WHERE tripID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tripID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                trip = new Trip(rs.getString("tripID"),  rs.getString("userID"), rs.getString("parkID"), rs.getString("date"));
                return trip;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding trip");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public ArrayList<Trip> findAll() throws DataAccessException {
        ArrayList<Trip> trips = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM trips;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip(rs.getString("tripID"), rs.getString("userID"), rs.getString("parkID"), rs.getString("date"));
                trips.add(trip);
            }
            return trips;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding trips");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void delete(String userID) throws DataAccessException {
        String sql = "DELETE FROM trips WHERE tripID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while deleting tripID");
        }
    }

    public void clearTables() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM trips";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
