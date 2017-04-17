package GISModule;

import java.util.Vector;

public class Room
{
    private int id;
    private String roomName;
    private int level;
    private double longitude;
    private double latitude;
    private int build_id;

    public static Vector<Room> roomVector = new Vector<Room>();


    public Room()
    {
        id = -1;
        roomName = "";
        level = -1;
        longitude = 0.0;
        latitude = 0.0;
        build_id = -1;
    }

    public Room(int i, String r, int l, double lon, double lat, int bd)
    {
        id = i;
        roomName = r;
        level = l;
        longitude = lon;
        latitude = lat;
        build_id = bd;
    }

    public int getId() {return id;}
    public void setId(int id_) {id = id_;}

    public String getRoomName() {return roomName;}
    public void setRoomName(String rname_) {roomName = rname_;}

    public int getLevel() {return level;}
    public void setLevel(int l) {level = l;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude_) {latitude = latitude_;}

    public double getLongitude() {return longitude;}
    public void setongitude(double longitude_) {longitude = longitude_;}

    public int getBuild_id() {return build_id;}
    public void setBuild_id(int bid) {build_id = bid;}

}
