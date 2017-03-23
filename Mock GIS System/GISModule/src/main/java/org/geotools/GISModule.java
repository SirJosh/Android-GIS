package org.geotools;

import java.util.Vector;

/**
 * Created by Josh on 2017/03/23.
 */
public class GISModule
{
    private String BuildingName;
    private Coordinates coordinates;

    Vector<GISModule> locations = new Vector<GISModule>();

    public GISModule()
    {
        BuildingName = "";
        coordinates  = new Coordinates();
    }

    public GISModule(String b, Double lon, Double lat, Double alt)
    {
        BuildingName = b;
        coordinates.setLongitude(lon);
        coordinates.setLatitude(lat);
        coordinates.setAltitude(alt);
    }


    // INSERT
    public void insertLocation(String b, Double lon, Double lat, Double alt)
    {
        GISModule building = new GISModule();

        building.BuildingName = b;
        building.coordinates.setLongitude(lon);
        building.coordinates.setLatitude(lat);
        building.coordinates.setAltitude(alt);

        locations.add(building);
    }

    public void hardCode()
    {
        GISModule Humanities_Building = new GISModule();
        Humanities_Building.insertLocation("Humanities_Building",-25.7554519,28.2310199, 0.00);

        GISModule Centenary_Building = new GISModule();
        Centenary_Building.insertLocation("Centenary Building", -25.7552513 , 28.2306343, 0.00);

        GISModule Information_Technology_Building = new GISModule();
        Information_Technology_Building.insertLocation("Information Technology Building", -25.7556415, 28.2319014, 0.00);

        GISModule Client_Service_Centre = new GISModule();
        Client_Service_Centre.insertLocation("Client_Service_Centre", -25.7556689, 28.2312997, 0.00);

        GISModule Theology_Building = new GISModule();
        Theology_Building.insertLocation("Theology Building", -25.7550951, 28.229788, 0.00);

        GISModule EMSB = new GISModule();
        EMSB.insertLocation("Economic and Management Sciences Building", -25.7556051, 28.2310267, 0.00);

        locations.add(Humanities_Building);
        locations.add(Centenary_Building);
        locations.add(Information_Technology_Building);
        locations.add(Client_Service_Centre);
        locations.add(Theology_Building);
        locations.add(EMSB);
    }

    public  String DisplayOne(String s)
    {
        return locations.firstElement().toString();
    }


    // REMOVE
    public void removeLocation(String s)
    {
        locations.removeElementAt(0);
    }


    // UPDATE
    public void updateLocationName(String s)
    {
        locations.removeElementAt(5);
        GISModule update = new GISModule("EMB", -25.7556051, 28.2310267, 0.00);
        locations.add(update);
    }

    public void updateLocationLong(String s)
    {
        locations.removeElementAt(5);
        GISModule update = new GISModule("Economic and Management Sciences Building", -23.7556051, 28.2310267, 0.00);
        locations.add(update);
    }

    public void updateLocationLat(String s)
    {
        locations.removeElementAt(5);
        GISModule update = new GISModule("Economic and Management Sciences Building", -25.7556051, 29.2310267, 0.00);
        locations.add(update);
    }

    public void updateLocationAlt(String s)
    {
        locations.removeElementAt(5);
        GISModule update = new GISModule("Economic and Management Sciences Building", -25.7556051, 29.2310267, 1.00);
        locations.add(update);
    }

    public String getLocationByCo(Double l, Double la, Double a)
    {
        return "Humanities Building";
    }

    public String getLocationByName(String buildingName_)
    {
        Double lon = -25.7554519;
        Double lat = 28.2310199;
        Double alt = 0.00;

        return (lon + " " + lat + " " + alt);
    }

    public static String Map()
    {
        return "THE MAP";
    }


    public String getPossibleWayPoints ()
    {
        return locations.toString();
    }

    public String toString()
    {
        String b =  locations.elementAt(0).BuildingName;
        Double lon = locations.elementAt(0).coordinates.getLongitude();
        Double lat = locations.elementAt(0).coordinates.getLatitude();
        Double alt = locations.elementAt(0).coordinates.getAltitude();

        return b + " " + lon + " " + lat + " " + alt;
    }

}