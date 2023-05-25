package Model;

import java.util.Objects;

public class Park {
    private String parkID;
    private String name;
    private Float latitude;
    private Float longitude;
    private String state;


    /**
     * Empty constructor for Park
     */
    public Park() {
        parkID = null;
        name = null;
        latitude = null;
        longitude = null;
        state = null;
    }

    /**
     * Park constructor with all parameters
     *
     * @param parkID
     * @param name
     * @param latitude
     * @param longitude
     * @param state
     */
    public Park(String parkID, String name, Float latitude, Float longitude, String state) {
        this.parkID = parkID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
    }

    public String getParkID() {
        return parkID;
    }

    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return Objects.equals(parkID, park.parkID) && Objects.equals(name, park.name) && Objects.equals(latitude, park.latitude) && Objects.equals(longitude, park.longitude) && Objects.equals(state, park.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkID, name, latitude, longitude, state);
    }

    @Override
    public String toString() {
        return "Park{" +
                "parkID='" + parkID + '\'' +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
