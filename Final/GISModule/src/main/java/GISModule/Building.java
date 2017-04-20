package GISModule;

import java.util.HashMap;
import java.util.Vector;

/**
 * The Building class is a wrapper used for our JSON strings. It structures our JSON strings and allows
 * other modules to interact with it in that format.
 *
 * e.g JSON String : {"id": 1, "name": "IT Building", "longitude": -25.000, "latitude": 28.000, "description": "building info"}.
 *
 * The HashMap acts as a wrapper so the user does not interact with the database directly.
 *
 * @author  GIS - Team Longsword
 * Joshua Moodley - 14152152
 * Boikanyo Modiko - 15227678
 * Mfana Masimula - 12077713
 * Bongani Tshela - 14134790
 *
 *
 * Accepted building Names in JSON string for name key:
 * IT Building,
 * EMB Building,
 * Client Service Centre,
 * Theology Building,
 * Centenary Building,
 * Humanities Building,
 * University of Pretoria Library,
 * Engineering 1,
 * Engineering 2,
 * Engineering 3,
 * Amphitheater,
 * Music Library,
 * Music Building,
 * Aula lawn,
 * AE du Toit auditorium / Annexe,
 * Mathematics Building,
 * Sci-Enza,
 * Thuto Building,
 * Chemistry Building,
 * Chancelors Building,
 * Architecture building,
 * Law Building,
 * Drama Building,
 * JJ Theron,
 * Law Building,
 * Natural and Agricultural Sciences Building
 */
public class Building
{
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    private String description;

    public HashMap<String, String> buildingsMap = new HashMap<String, String>();
    /**
     * A temporary structure used for JSON array conversions that holds building objects
     */
    public static Vector<Building> buildingVector = new Vector<Building>();

    /**
     * Building() : Constructor
     */
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

    /**
     * Building(...) : Parametrised constructor
     * @param i     id
     * @param n     building name
     * @param lon   longitude
     * @param lat   latitude
     * @param desc  description
     */
    public Building(int i, String n, Double lon, Double lat, String desc)
    {
        id = i;
        name = n;
        longitude = lon;
        latitude = lat;
        description = desc;

    }

    /**
     * Building(...) : Parametrised constructor
     * @param n     building name
     * @param lon   longitude
     * @param lat   latitude
     * @param desc  description
     */
    public Building(String n, Double lon, Double lat, String desc)
    {
        name = n;
        longitude = lon;
        latitude = lat;
        description = desc;
    }

    /**
     * Getter
     * @return id
     */
    public int getId() {return id;}

    /**
     * Setter
     * @param id_ id
     */
    public void setId(int id_) {id = id_;}

    /**
     * Getter
     * @return building name
     */
    public String getName() {return name;}

    /**
     * Setter
     * @param name_ building name
     */
    public void setName(String name_) {name = name_;}

    /**
     * Getter
     * @return building description
     */
    public String getDescription() {return description;}

    /**
     * Setter
     * @param description_ building description
     */
    public void setDescription(String description_) {description = description_;}

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
    public void setLongitude(double longitude_) {longitude = longitude_;}

}
