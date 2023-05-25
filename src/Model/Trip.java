package Model;

import java.util.Objects;

public class Trip {

    private String trip_id;
    private String user_id;
    private String park_id;
    private String date;

    /**
     * empty constructor for User
     */
    public Trip() {
        trip_id = null;
        user_id = null;
        park_id = null;
        date = null;
    }

    /**
     * User constructor with all parameters
     * @param trip_id
     * @param user_id
     * @param park_id
     * @param date
     */
    public Trip(String trip_id, String user_id, String park_id, String date) {
        this.trip_id = trip_id;
        this.user_id = user_id;
        this.park_id = park_id;
        this.date = date;
    }

    public String getTripID() {
        return trip_id;
    }

    public void setTripID(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String user_id) {
        this.user_id = user_id;
    }

    public String getParkID() {
        return park_id;
    }

    public void setParkID(String park_id) {
        this.park_id = park_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return (Objects.equals(user_id, trip.user_id) &&
                Objects.equals(park_id, trip.park_id) &&
                Objects.equals(date, trip.date));
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, park_id, date);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "user_id='" + user_id + '\'' +
                ", park_id='" + park_id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}