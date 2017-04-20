package GISModule;

import java.util.Vector;

/**
 * The Room class is a wrapper used for our JSON strings. It structures our JSON strings and allows
 * other modules to interact with it in that format.
 *
 * e.g JSON String : {"id": 1, "roomName": "IT 4-1", "level": 4, "longitude": -25.000, "latitude": 28.000, "build_id": 1}.
 *
 * The build_id is a FK to our buildings tables which holds all of the buildings on campus. The room tables e.g. IT_building
 * hold the rooms to that building. This is how we link the rooms to the building.
 *
 * @author  GIS - Team Longsword
 * Joshua Moodley - 14152152
 * Boikanyo Modiko - 15227678
 * Mfana Masimula - 12077713
 * Bongani Tshela - 14134790
 *
 * Accepted Room names in JSON string for roomName key:
 * IT 2-23,
 * IT 2-24,
 * IT 2-25,
 * IT 2-26,
 * IT 2-27,
 * IT 4-1,
 * IT 4-2,
 * IT 4-3,
 * IT 4-4,
 * IT 4-5,
 * EB/EMB 2-150,
 * EB/EMB 2-151,
 * EB/EMB 4-150,
 * EB/EMB 4-151,
 * EB/EMB 4-152
 */
public class Room
{

    private int id;
    private String roomName;
    private int level;
    private double longitude;
    private double latitude;
    private int build_id;

    /**
     * A temporary structure used for JSON array conversions that holds room objects.
     */
    public static Vector<Room> roomVector = new Vector<Room>();


    /**
     * Room() : Constructor
     */
    public Room()
    {
        id = -1;
        roomName = "";
        level = -1;
        longitude = 0.0;
        latitude = 0.0;
        build_id = -1;
    }

    /**
     * Room(...) : Parametrised constructor
     *
     * @param i     id
     * @param r     room
     * @param l     level
     * @param lon   longitude
     * @param lat   latitude
     * @param bd    building id (fk)
     */
    public Room(int i, String r, int l, double lon, double lat, int bd)
    {
        id = i;
        roomName = r;
        level = l;
        longitude = lon;
        latitude = lat;
        build_id = bd;
    }

    /**
     * Getter
     * @return id
     */
    public int getId() {return id;}

    /**
     * Setter
     * @param id_   id
     */
    public void setId(int id_) {id = id_;}

    /**
     * Getter
     * @return room name (IT 4-1)
     */
    public String getRoomName() {return roomName;}

    /**
     * Setter
     * @param rname_ room name (IT 4-1)
     */
    public void setRoomName(String rname_) {roomName = rname_;}

    /**
     * Getter
     * @return level
     */
    public int getLevel() {return level;}

    /**
     * Setter
     * @param l level (floor level)
     */
    public void setLevel(int l) {level = l;}

    /**
     * Getter
     * @return latitude
     */
    public double getLatitude() {return latitude;}

    /**
     * Setter
     * @param latitude_ latitude
     */
    public void setLatitude(double latitude_) {latitude = latitude_;}

    /**
     * Getter
     * @return longitude
     */
    public double getLongitude() {return longitude;}

    /**
     * Setter
     * @param longitude_ longitude
     */
    public void setongitude(double longitude_) {longitude = longitude_;}

    /**
     * Getter
     * @return FK building id
     */
    public int getBuild_id() {return build_id;}

    /**
     * Setter
     * @param bid building id
     */
    public void setBuild_id(int bid) {build_id = bid;}

}
