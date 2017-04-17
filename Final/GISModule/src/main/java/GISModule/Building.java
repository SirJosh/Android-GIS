package GISModule;

import java.util.HashMap;
import java.util.Vector;

public class Building
{
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    private String description;

    public HashMap<String, String> buildingsMap = new HashMap<String, String>();
    public static Vector<Building> buildingVector = new Vector<Building>();

    public Building()
    {
        id = -1;
        name = "";
        description = "";
        latitude = 0.0;
        longitude = 0.0;
        buildingsMap.put("IT Building", "IT_building");
        buildingsMap.put("EMB Building", "EMB_building");
    }

    public Building(int i, String n, Double lon, Double lat, String desc)
    {
        id = i;
        name = n;
        longitude = lon;
        latitude = lat;
        description = desc;

    }

    public Building(String n, Double lon, Double lat, String desc)
    {
        name = n;
        longitude = lon;
        latitude = lat;
        description = desc;
    }

    public int getId() {return id;}
    public void setId(int id_) {id = id_;}

    public String getName() {return name;}
    public void setName(String name_) {name = name_;}

    public String getDescription() {return description;}
    public void setDescription(String description_) {description = description_;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude_) {latitude = latitude_;}

    public double getLongitude() {return longitude;}
    public void setongitude(double longitude_) {longitude = longitude_;}

}
