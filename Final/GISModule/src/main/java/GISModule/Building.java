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
    public Vector<Building> buildingVector = new Vector<Building>();

    public Building()
    {
        id = -1;
        name = "";
        description = "";
        latitude = 0.0;
        longitude = 0.0;
        buildingsMap.put("IT Building","IT_building");
        buildingsMap.put("EMB Building","EMB_building");

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

}
