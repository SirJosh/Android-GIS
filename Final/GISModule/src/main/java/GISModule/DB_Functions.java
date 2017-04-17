package GISModule;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Josh - 4
// Bk - 3
// Mambane - 4
// Mfana - 3

/**
 * The DB_Functions class provides the necessary functions for interaction
 * between other modules and the GIS module.
 *
 * Refer to documentation for building name and room name conventions
 *
 * @author  GIS - Team Longsword
 * @version 4.0
 * @since   3.0 11-04-2017
 * @since   4.0 16-04-2017
 */

public class DB_Functions
{
    /**
     * Mambane
     *
     * insertBuilding(buildingName, desc, lat, lon) - insert a new location
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of new location e.g. IT Building
     * desc         Building and room description / purpose
     * lat          Latitude of location
     * lon          Longitude of location
     *
     * @param jsonString   JSON String with above parameters
     */
    public void insertBuilding(String jsonString)
    {

    }

    /**
     * Mambane
     *
     * insertBuildingRoom(buildingName, room, desc, lat, lon) - insert a new location
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of new location e.g. IT Building
     * room         Room number in the form of "IT 4-1" or "EMB 2-151"
     * desc         Building and room description / purpose
     * lat          Latitude of location
     * lon          Longitude of location
     *
     * @param jsonString JSON String with above parameters
     */
    public void insertBuildingRoom(String jsonString)
    {

    }

    /**
     * Mambane
     *
     * updateBuildingName(oldBuildingName, newBuildingName) - update an existing locations name
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * oldBuildingName Old building name of location e.g. IT Building
     * newBuildingName New building name of location e.g. Information Technology Building
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingName(String jsonString)
    {

    }

    /**
     * Mambane
     *
     * updateBuildingRoom(buildingName, oldRoom, newRoom) - update an existing building room name
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of location to edit e.g. IT Building
     * oldRoom      Old room number in the form of "IT 4-1" or "EMB 2-151"
     * newRoom      New room number in the form of "IT 4-1" or "EMB 2-151"
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingRoom(String jsonString)
    {

    }

    /**
     * BK
     *
     * updateBuildingCoordinates(buildingName, lat, lon) - update coordinates of a location
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of location e.g. IT Building
     * lat          New latitude of location
     * lon          New longitude of location
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingCoordinates(String jsonString)
    {
    }

    /**
     * Mfana
     *
     * updateBuildingRoomCoordinates(buildingName, room, lat, lon) - update coordinates of a location
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of location e.g. IT Building
     * room         Room number to edit in the form of "IT 4-1" or "EMB 2-151"
     * lat          New latitude of location
     * lon          New longitude of location
     *
     * @param jsonString JSON String with above parameters
     */
    public void UpdateBuildingRoomCoordinates(String jsonString)
    {

    }

    /**
     * Mfana
     * removeBuilding(buildingName) - remove an existing location
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of location e.g. IT Building
     *
     * @param jsonString JSON String with above parameters
     */
    public void removeBuilding(String jsonString)
    {

    }

    /**
     * Mfana
     *
     * removeBuildingRoom(buildingName, room) - remove an existing locations room
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of location e.g. IT Building
     * room         Room number to remove in the form of "IT 4-1" or "EMB 2-151"
     *
     * @param jsonString JSON String with above parameters
     */
    public void removeBuildingRoom(String jsonString)
    {

    }

    /**
     * Joshua
     *
     * listBuildings() - returns all the buildings available on main campus
     *
     * Uses class Building to create the JSON objects
     *
     * @return JSON string array
     */
    public String listBuildings()
    {
        // Connection
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM public.buildings";

            ResultSet rs = stmt.executeQuery( sql);

            while ( rs.next() )
            {
                int id = rs.getInt("id");
                String bName = rs.getString("name");
                Double lat = rs.getDouble("latitude");
                Double lon = rs.getDouble("longitude");
                String descr = rs.getString("description");

                Building b = new Building(id, bName, lon, lat, descr);
                Building.buildingVector.add(b);
            }

            rs.close();
            stmt.close();
            c.close();

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Search for All building done successfully");

        // Create the json array from the vector
        Gson jsonObj = new Gson();

        return jsonObj.toJson(Building.buildingVector);
    }

    /**
     * Joshua
     * listRoomNames(buildingName) - returns all rooms available in the building, provided that that the building name is given
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * Uses a HashMap to link the building name to a table name in the database,
     * along with the class Room to create the JSON objects
     *
     * buildingName Building name of location e.g. IT Building
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON string array
     */
    public String listRoomNames(String jsonString)
    {
        // JSON Conversion
        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        String buildingNameSearch = buildingObj.getName();
        String tableBuilding = buildingObj.buildingsMap.get(buildingNameSearch);

        // Connection
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM public." + tableBuilding;

            ResultSet rs = stmt.executeQuery( sql);

            while ( rs.next() )
            {
                int id = rs.getInt("id");
                String rName = rs.getString("roomName");
                int lvl = rs.getInt("level");
                Double latt = rs.getDouble("latitude");
                Double lonn = rs.getDouble("longitude");
                int build_id = rs.getInt("build_id");

                Room rm = new Room(id, rName, lvl, lonn, latt, build_id);
                Room.roomVector.add(rm);
            }

            rs.close();
            stmt.close();
            c.close();

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Search for All rooms in a building done successfully");

        // Create the json array from the vector
        return jsonObj.toJson(Room.roomVector);
    }


    /**
     * Joshua
     * getBuildingByName(buildingName) - gets all information regarding a specific building
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * buildingName Building name of location e.g. IT Building
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON string
     */
    public String getBuildingByName(String jsonString)
    {
        // JSON Conversion
        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        String buildingNameSearch = buildingObj.getName();
        String returnJsonString = "";

        // Connection
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM PUBLIC.buildings WHERE name = " + "'" + buildingNameSearch + "'";

            ResultSet rs = stmt.executeQuery( sql);

            while ( rs.next() )
            {
                int id = rs.getInt("id");
                String bName = rs.getString("name");
                Double lat = rs.getDouble("latitude");
                Double lon = rs.getDouble("longitude");
                String descr = rs.getString("description");

                Building b = new Building(id, bName, lon, lat, descr);
                returnJsonString = jsonObj.toJson(b);
            }

            rs.close();
            stmt.close();
            c.close();

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Search for building by name done successfully");

        return returnJsonString;
    }

    /**
     * Joshua
     *
     * getBuildingByCoordinates(lat, lon) - get all information on a building based on the coordinates provided
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * lat Latitude of location
     * lon Longitude of location
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON string
     */
    public String getBuildingByCoordinates(String jsonString)
    {
        // TODO Where I will start again
        Gson g = new Gson();
        String jsonS = "";
        Double lat = 0.0, lon = 0.0;

        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM public.buildings " +
                         "WHERE latitude::text LIKE " + "'" + lat + "%'" +
                         "AND longitude::text LIKE " + "'" + lon + "%'";

            ResultSet rs = stmt.executeQuery( sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String bName = rs.getString("name");
                Double latt = rs.getDouble("latitude");
                Double lonn = rs.getDouble("longitude");
                String descr = rs.getString("description");

                Building bC = new Building(id, bName, lonn, latt, descr);
                jsonS += g.toJson(bC) + ",";
            }

            rs.close();
            stmt.close();
            c.close();

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Search for building by coordinates done successfully");
        return jsonS;
    }

    /**
     * BK
     *
     * getLocationByRoomNumber(buildingName , room) - gets a location of a room by providing the building name and room
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * Uses a HashMap to link the building name to a table name in the database,
     * along with the class Room to create the JSON objects
     *
     * buildingName Building name of location e.g. IT Building
     * room         Room number in the form of "IT 4-1" or "EMB 2-151"
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON String
     */
    public String getLocationByRoomNumber(String jsonString)
    {
        Gson r = new Gson();
        String jsonS = "";
        Building b = new Building();

        Connection c = null;
        Statement stmt = null;
        String buildingName = null;
        String room = null;

        String build = b.buildingsMap.get(buildingName);

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM public."+ build + " WHERE roomname =" + "'" + room+ "'";

            ResultSet rs = stmt.executeQuery( sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String rName = rs.getString("roomName");
                int lvl = rs.getInt("level");
                Double latt = rs.getDouble("latitude");
                Double lonn = rs.getDouble("longitude");
                int build_id = rs.getInt("build_id");

                Room rm = new Room(id, rName, lvl, lonn, latt, build_id);
                jsonS += r.toJson(rm) + ",";
            }

            rs.close();
            stmt.close();
            c.close();

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Search for building room  by name done successfully");
        return jsonS;

    }


    /**
     * BK
     *
     * getRoutes(sLat, sLong, dLat, dLong) - provides all possible routes between a start and end point for a user
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide
     *
     * sLat          Start latitude
     * sLong         Start longitude
     * dLat          Destination latitude
     * dLong         Destination longitude
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON String array
     */
    public String getRoutes(String jsonString)
    {
        return "";
    }
}