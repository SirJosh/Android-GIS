package GISModule;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Josh - 4
// Bk - 3
// Bongani - 4
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
     * Bongani
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
                        //connection
                        Connection con = null;
                        PreparedStatement stmt = null;
                        String url = "jdbc:postgresql://localhost:10000/tempnavup";
                        String user = "postgres";
                        String password = "password";

                        // JSON Conversion
                        Gson jsonObj = new Gson();
                        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);


                        String buildingName = buildingObj.getName();
                        String desc = buildingObj.getDescription();
                        double lat = buildingObj.getLatitude();
                        double longi = buildingObj.getLongitude();

                        // Clear Vectors
                        Building.buildingVector.clear();
                        Room.roomVector.clear();

                        try {

                            Class.forName("org.postgresql.Driver");
                            con = DriverManager.getConnection(url,user,password);
                            if(con == null) System.out.println("connection is junk");
                            con.setAutoCommit(false);
                            //System.out.println("Opened database successfully");

                            //stmt = c.createStatement();
                            String sql = "INSERT INTO buildings (name,latitude,longitude,description) VALUES (?,?,?,?);";
                            //System.out.println("Done declaring sql");
                            stmt = con.prepareStatement(sql);
                            stmt.setString(1,buildingName);
                            stmt.setDouble(2,lat);
                            stmt.setDouble(3,longi);
                            stmt.setString(4,desc);


                            stmt.executeUpdate();
                            System.out.println("Done inserting");

                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);


                        } finally {
                            try{
                                if(stmt != null) stmt.close();
                                if(con != null) con.close();
                            } catch (Exception e){

                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);

                            }
                        }
    }

    /**
     * Bongani
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
                        //String s = String.format("string1%sI am a string", jsonString);--another way to apeend strings if the one I used does not work

                        //connection
                        Connection con = null;
                        PreparedStatement stmt = null;
                        String url = "jdbc:postgresql://localhost:10000/tempnavup";
                        String user = "postgres";
                        String password = "password";

                        // JSON Conversion
                        Gson jsonObj = new Gson();
                        Gson jsonObj2 = new Gson();
                        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
                        Room roomObj = jsonObj.fromJson(jsonString, Room.class);

                        Building b = new Building();

                        String buildingNameSearch = buildingObj.getName();
                        String room = roomObj.getRoomName();
                        int level = roomObj.getLevel();
                        double lat = roomObj.getLatitude();
                        double longi = roomObj.getLongitude();

                        String build = b.buildingsMap.get(buildingNameSearch);

                        // Clear Vectors
                        Building.buildingVector.clear();
                        Room.roomVector.clear();

                        try {

                            Class.forName("org.postgresql.Driver");
                            con = DriverManager.getConnection(url,user,password);
                            if(con == null) System.out.println("connection is junk");
                            con.setAutoCommit(false);
                            System.out.println("Opened database successfully");

                            //stmt = c.createStatement();
                            String sql;
                            sql = new StringBuilder().append("INSERT INTO ").append(build).append(" (roomName,level,latitude,longitude) VALUES (?,?,?,?);").toString();
                           // System.out.println("Done declaring sql");
                            stmt = con.prepareStatement(sql);
                            stmt.setString(1,room);
                            stmt.setInt(1,level);
                            stmt.setDouble(3,lat);
                            stmt.setDouble(4,longi);



                            stmt.executeUpdate();
                            //System.out.println("Done inserting");

                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);


                        } finally {
                            try{
                                if(stmt != null) stmt.close();
                                if(con != null) con.close();
                            } catch (Exception e){

                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);

                            }
                        }


    }

    /**
     * Bongani
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
                        // Clear Vectors
                        Building.buildingVector.clear();
                        Room.roomVector.clear();

                        Gson jsonObj = new Gson();
                        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

                        String buildingName = buildingObj.getName();
                        String[] names = gson.fromJson(jsonString,String[].class);

                        String originalRoom = names[0];
                        String changeName = names[1];




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

                            String sql = new StringBuilder().append("UPDATE public.buildings ").append("SET name =")
                                    .append("'").append(changeName).append("'").append("WHERE name = ").append(originalRoom).toString();
                                    //I used the append function(stringBuilder) ... if it does not work, the normal one in  in the comment bellow.

                           /* String sql = "UPDATE public.buildings " +
                                    "SET name ="+"'"+changeName+"'"+
                                    "WHERE name = "+originalRoom;*/

                            ResultSet rs = stmt.executeQuery( sql);

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

    }

    /**
     * Bongani
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
                        // Clear Vectors
                        Building.buildingVector.clear();
                        Room.roomVector.clear();

                        Gson jsonObj = new Gson();
                        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

                        String buildingName = buildingObj.getName();
                        String[] names = gson.fromJson(jsonString,String[].class);

                        String table = names[0];
                        String originalRoom = names[1];
                        String changeName = names[2];




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

                            /*String sql = new StringBuilder().append("UPDATE public.buildings ").append("SET name =")
                                    .append("'").append(changeName).append("'").append("WHERE name = ").append(originalRoom).toString();
                            //I used the append function(stringBuilder) ... if it does not work, the normal one in  in the comment bellow.*/

                            String sql = "UPDATE public."+table+"SET name ="+"'"+changeName+"'"+
                                    "WHERE name = "+originalRoom;

                            ResultSet rs = stmt.executeQuery( sql);

                            rs.close();
                            stmt.close();
                            c.close();

                        }
                        catch ( Exception e )
                        {
                            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                            System.exit(0);
                        }

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
        //Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        String buildingName = buildingObj.getName();
        double latitude = buildingObj.getLatitude();
        double longitude = buildingObj.getLongitude();

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

            String sql = "UPDATE public.buildings " +
                        "SET longitude ="+"'"+longitude+"'"+", latitude ="+"'"+latitude+"'"+
                        "WHERE name = "+buildingName;

            ResultSet rs = stmt.executeQuery( sql);

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
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();


        Gson jsonObj =new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        Room roomObj =jsonObj.fromJson(jsonString,Room.class);

        String buildingName=buildingObj.getName();
        String roomname=roomObj.getRoomName();
        double latitude = roomObj.getLatitude();
        double longitude = roomObj.getLongitude();
        String tableBuilding = buildingObj.buildingsMap.get(buildingName);
        
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/tempnavup","postgres", "1234");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "UPDATE public."+tableBuilding  +
                    " SET longitude ="+"'"+longitude+"'"+", latitude ="+"'"+latitude+"'"+
                    "WHERE name = "+roomname;

            ResultSet rs = stmt.executeQuery( sql);

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
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
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();
        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        String buildingName = buildingObj.getName();
        String tableBuilding = buildingObj.buildingsMap.get(buildingName);
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/tempnavup","postgres", "1234");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql1="DROP table "+tableBuilding+";"+"DELETE from public.buildings " + "WHERE name = "+buildingName;
            ResultSet rs=stmt.executeQuery(sql1);
            /*String sql = "DELETE from public.buildings " +
                    "WHERE name = "+buildingName;


            ResultSet rs = stmt.executeQuery( sql);*/

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
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
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();
        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        Room roomObj=jsonObj.fromJson(jsonString,Room.class);


        String buildingName = buildingObj.getName();
        String room=roomObj.getRoomName();
        String tableBuilding = buildingObj.buildingsMap.get(buildingName);


        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/tempnavup","postgres", "1234");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "DELETE public."+tableBuilding+
                         " WHERE roomName = "+ "'" +  room + "'";
             stmt.executeQuery( sql);

            //rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
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

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

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

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

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

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

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
        // JSON Conversion
        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        Building b = new Building();

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        int count = -1;

        Double lat = buildingObj.getLatitude(), lon = buildingObj.getLongitude();

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

            ResultSet rs = stmt.executeQuery(sql);
            count = rs.getFetchSize();

            while (rs.next())
            {
                int id = rs.getInt("id");
                String bName = rs.getString("name");
                Double latt = rs.getDouble("latitude");
                Double lonn = rs.getDouble("longitude");
                String descr = rs.getString("description");

                if (count > 0)
                {
                    Building tempB = new Building(id, bName, lon, lat, descr);
                    // Add to array
                    Building.buildingVector.add(tempB);

                }
                else
                {
                    if(count == 0)
                    {
                        Building tempB = new Building(id, bName, lon, lat, descr);
                        Building.buildingVector.add(tempB);
                    }

                    b.setId(id);
                    b.setName(bName);
                    b.setLatitude(latt);
                    b.setLongitude(lonn);
                    b.setDescription(descr);
                }

                count++;
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

        if(count > -1)
        {
            return jsonObj.toJson(Building.buildingVector);
        }
        else
        {
            return jsonObj.toJson(b);
        }
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

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

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
         String returnJsonString = "";
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

      //  Gson jsonObj = new Gson();
       // Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        double sLat;
        double sLong;
        double dLat;
        double dLong;

        // Connection
        Connection c = null;
        Statement stmt = null;

        Gson jsonObj = new Gson();
        String[] string = gson.fromJson(jsonString,String[].class);

        sLat = Double.parseDouble(string[0]);
        sLong = Double.parseDouble(string[1]);
        dLat = Double.parseDouble(string[2]);
        dLong = Double.parseDouble(string[3]);

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM PUBLIC.rooms WHERE longitude BETWEEN " + "'" + sLong + "' AND '"+dLong+"'"
                    +"OR latitude BETWEEN " + "'" + sLat + "' AND '"+dLat+"'";

            ResultSet rs = stmt.executeQuery( sql);

            while ( rs.next() )
            {
                int id = rs.getInt("id");
                String roomName = rs.getString("roomName");
                int level = rs.getInt("level");
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                int build_id = rs.getInt("build_id");

                Room b = new Room(id, roomName, level, latitude, longitude, build_id);
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

        return "";
    }
}
