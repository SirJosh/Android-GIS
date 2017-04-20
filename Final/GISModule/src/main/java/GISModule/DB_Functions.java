package GISModule;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.*;

/**
 * The DB_Functions class provides the necessary functions for interaction
 * between other modules and the GIS module.
 *
 * Run the tempnavup.sql in POSTGRES SQL with the postGIS extensions.
 *
 * Refer to the building and room class for building name and room name conventions.
 *
 * Gson is used for all our JSON conversions, check pom.xml for maven dependencies.
 *
 * @author  GIS - Team Longsword
 * @version 6.0
 * @since   3.0 : 11-04-2017
 * @since   4.0 : 16-04-2017
 * @since   5.0 : 19-04-2017
 * @since   6.0 : 20-04-2017
 */

public class DB_Functions
{
    // Connection
    private Connection c = null;
    private Statement stmt = null;
    private String url = "jdbc:postgresql://localhost:10000/tempnavup"; // CHANGE PORT NUMBER HERE
    private String user = "postgres";
    private String password = "password";                               // CHANGE PASSWORD HERE

    /**
     * insertBuilding(buildingName, desc, lat, lon) - insert a new location.
     *
     * JSON Format : "{"name":"One Building","longitude":-24.7556415,"latitude":27.2319014,"description":"This is the one building"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName Building name of new location e.g. IT Building
     * desc         Building and room description / purpose
     * lat          Latitude of location
     * lon          Longitude of location
     *
     * @param jsonString   JSON String with above parameters
     */
    public void insertBuilding(String jsonString) // FINISHED
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        PreparedStatement pstmt = null;


        // JSON Conversion
        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        String buildingName = buildingObj.getName();
        String desc = buildingObj.getDescription();
        double lat = buildingObj.getLatitude();
        double longi = buildingObj.getLongitude();

        try
        {

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            if(c == null)
            {
                System.out.println("connection error");
            }

            String sql = "INSERT INTO public.buildings (name,latitude,longitude,description) " +
                         "VALUES" + "(" + ("'" + buildingName + "'" + "," + lat + "," + longi + "," +  "'" + desc + "'") + ")";

            stmt.executeUpdate(sql);

            System.out.println("Insert building done successfully");

        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally
        {
            try
            {
                if(stmt != null)
                    stmt.close();
                if(c != null)
                    c.close();
                if(pstmt != null)
                    pstmt.close();
            }
            catch (Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    /**
     * insertBuildingRoom(buildingName, room, desc, lat, lon, level) - insert a new location.
     *
     * JSON Format : "{"name":"One Building", "roomName": "One 1-1"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName Building name of new location e.g. IT Building
     * room         Room number in the form of "IT 4-1" or "EMB 2-151"
     * desc         Building and room description / purpose
     * lat          Latitude of location
     * lon          Longitude of location
     * level        Room level
     *
     * @param jsonString JSON String with above parameters
     */
    public void insertBuildingRoom(String jsonString) // FINISHED
    {

        PreparedStatement pstmt = null;

        // JSON Conversion
        Gson jsonObj = new Gson();
        Gson jsonObj2 = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        Room roomObj = jsonObj.fromJson(jsonString, Room.class);

        Building b = new Building();

        String buildingNameSearch = buildingObj.getName();
        int fk_id = buildingObj.getId();

        String room = roomObj.getRoomName();
        int level = roomObj.getLevel();
        double lat = roomObj.getLatitude();
        double longi = roomObj.getLongitude();


        String build = b.buildingsMap.get(buildingNameSearch);

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");


            if(buildingNameSearch.equals("IT Building"))
            {
                String sql;
                sql = new StringBuilder().append("INSERT INTO public.").append(build).append(" (roomName,level,latitude,longitude, build_id) VALUES (?,?,?,?,?);").toString();

                fk_id = 1;

                pstmt = c.prepareStatement(sql);
                pstmt.setString(1,room);
                pstmt.setInt(2,level);
                pstmt.setDouble(3,lat);
                pstmt.setDouble(4,longi);
                pstmt.setInt(5,fk_id);

                pstmt.execute();

                System.out.println("Insert building room done successfully");

                c.close();
            }
            else
            {
                String sql;
                sql = new StringBuilder().append("INSERT INTO public.").append(build).append(" (roomName,level,latitude,longitude, build_id) VALUES (?,?,?,?,?);").toString();

                fk_id = 2;

                pstmt = c.prepareStatement(sql);
                pstmt.setString(1,room);
                pstmt.setInt(2,level);
                pstmt.setDouble(3,lat);
                pstmt.setDouble(4,longi);
                pstmt.setInt(5,fk_id);

                pstmt.execute();

                System.out.println("Insert building room done successfully");

                c.close();
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        finally
        {
            try
            {
                if(stmt != null)
                    stmt.close();
                if(pstmt != null)
                    pstmt.close();
            }
            catch (Exception e)
            {

                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);

            }
        }
    }

    /**
     * updateBuildingName(oldBuildingName, newBuildingName) - update an existing locations name.
     *
     * JSON Format : "{"oldBuildingName":"One Building", "newBuildingName": "ONE Building"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * oldBuildingName: Old building name of location e.g. IT Building.
     * newBuildingName:  New building name of location e.g. Information Technology Building.
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingName(String jsonString) // FINISHED
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        Gson returnJson = new Gson();

        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        String originalRoom = jsonObject.get("oldBuildingName").getAsString();
        String changeName = jsonObject.get("newBuildingName").getAsString();

        // Connection
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            //String sql = new StringBuilder().append("UPDATE public.buildings ").append("SET name =").append(" '").append(changeName).append("'").append("WHERE name = ").append(originalRoom).toString();
            //I used the append function(stringBuilder) ... if it does not work, the normal one in  in the comment bellow.

            // UPDATE public.buildings SET name = 'Bubbles' WHERE name = ' Information Technology Building';
            String sql = "UPDATE public.buildings " + "SET name = "+"'"+changeName+"'"+ " WHERE name = " + "'"+ originalRoom + "'";

            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

            System.out.println("Update building name done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * updateBuildingRoom(buildingName, oldRoom, newRoom) - update an existing building room name.
     *
     * JSON Format : "{"buildingName":"IT Building", "oldRoom": "IT 4-5", "newRoom": "IT 4-6"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName: Building name of location to edit e.g. IT Building.
     * oldRoom:      Old room number in the form of "IT 4-1" or "EMB 2-151".
     * newRoom:      New room number in the form of "IT 4-1" or "EMB 2-151".
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingRoom(String jsonString) // FINISHED
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        JsonObject jsonObject = new Gson().fromJson(jsonString,JsonObject.class);
        String jsonReturn = "";


        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);


        String buildingName = jsonObject.get("buildingName").getAsString();
        String build = buildingObj.buildingsMap.get(buildingName);

        String originalRoom = jsonObject.get("oldRoom").getAsString();
        String changeName = jsonObject.get("newRoom").getAsString();

        // Connection
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

                            /*String sql = new StringBuilder().append("UPDATE public.buildings ").append("SET name =")
                                    .append("'").append(changeName).append("'").append("WHERE name = ").append(originalRoom).toString();
                            //I used the append function(stringBuilder) ... if it does not work, the normal one in  in the comment bellow.*/

            String sql = "UPDATE public."+build+" SET roomName ="+"'"+changeName+"'"+
                         "WHERE roomName = "+ "'" + originalRoom + "'";

            stmt.executeUpdate( sql);

            stmt.close();
            c.close();

            System.out.println("Update building room done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * updateBuildingCoordinates(buildingName, lat, lon) - update coordinates of a location.
     *
     * JSON Format : "{"buildingName":"IT Building", "latitude": "-23.2323", "longitude": "28.05678"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName: Building name of location e.g. IT Building.
     * lat:          New latitude of location.
     * lon:          New longitude of location.
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingCoordinates(String jsonString) // FINISHED
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
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "UPDATE public.buildings " +
                         "SET longitude ="+"'"+longitude+"'"+", latitude ="+"'"+latitude+"'"+
                         "WHERE name = '"+buildingName+"'";


            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

            System.out.println("Update building by coordinates done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * updateBuildingRoomCoordinates(buildingName, room, lat, lon) - update coordinates of a location.
     *
     * JSON Format : "{"buildingName":"IT Building", "roomName":"IT 4-5", "latitude": "-23.2323", "longitude": "28.05678"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName: Building name of location e.g. IT Building.
     * room:         Room number to edit in the form of "IT 4-1" or "EMB 2-151".
     * lat:          New latitude of location.
     * lon:          New longitude of location.
     *
     * @param jsonString JSON String with above parameters
     */
    public void updateBuildingRoomCoordinates(String jsonString) // FINISHED
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();


        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        Room roomObj =jsonObj.fromJson(jsonString,Room.class);

        String buildingName = buildingObj.getName();
        String roomname = roomObj.getRoomName();
        double latitude = roomObj.getLatitude();
        double longitude = roomObj.getLongitude();

        String tableBuilding = buildingObj.buildingsMap.get(buildingName);


        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "UPDATE public."+tableBuilding  +
                         " SET longitude =" +longitude+ ",latitude ="+latitude+
                         " WHERE roomName = '"+ roomname + "'";

            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

            System.out.println("Update building room coordinates done successfully");

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * removeBuilding(buildingName) - remove an existing location.
     *
     * JSON Format : "{"name":"Humanities Building"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName: Building name of location e.g. IT Building.
     *
     * @param jsonString JSON String with above parameters
     */
    public void removeBuilding(String jsonString) // FINISHED
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        Gson jsonObj = new Gson();
        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);

        String buildingName = buildingObj.getName();
        String tableBuilding = buildingObj.buildingsMap.get(buildingName);

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "DELETE from public.buildings " + "WHERE name = "+"'"+buildingName+"'";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

            System.out.println("Remove building done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * removeBuildingRoom(buildingName, room) - remove an existing locations room.
     *
     * JSON Format : "{"name":"IT Building", "roomName": "IT 4-5"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName: Building name of location e.g. IT Building.
     * room:         Room number to remove in the form of "IT 4-1" or "EMB 2-151".
     *
     * @param jsonString JSON String with above parameters
     */
    public void removeBuildingRoom(String jsonString) // FINISHED
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
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(true);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            //System.out
            String sql = "DELETE FROM public."+ tableBuilding +
                         " WHERE roomname = "+ "'" +  room + "'";

            stmt.executeUpdate( sql);

            //rs.close();
            stmt.close();
            c.close();

            System.out.println("Remove building room done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * listBuildings() - returns all the buildings available on main campus.
     *
     * Uses class Building to create the JSON objects.
     *
     * @return JSON string array
     */
    public String listBuildings() // FINISHED
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
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

            System.out.println("Search for All building done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        // Create the json array from the vector
        Gson jsonObj = new Gson();

        return jsonObj.toJson(Building.buildingVector);
    }

    /**
     * listRoomNames(buildingName) - returns all rooms available in the building, provided that that the building name is given.
     *
     * JSON Format : "{"name":"IT Building";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * Uses a HashMap to link the building name to a table name in the database,
     * along with the class Room to create the JSON objects.
     *
     * buildingName: Building name of location e.g. IT Building.
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON string array
     */
    public String listRoomNames(String jsonString) // FINISHED
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
            c = DriverManager.getConnection(url, user, password);
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
            System.out.println("Search for All rooms in a building done successfully");

        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        // Create the json array from the vector
        return jsonObj.toJson(Room.roomVector);
    }


    /**
     * getBuildingByName(buildingName) - gets all information regarding a specific building.
     *
     * JSON Format : "{"name":"IT Building}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * buildingName: Building name of location e.g. IT Building.
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON string
     */
    public String getBuildingByName(String jsonString) // FINISHED
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
            c = DriverManager.getConnection(url, user, password);
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

            System.out.println("Search for building by name done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return returnJsonString;
    }

    /**
     * getBuildingByCoordinates(lat, lon) - get all information on a building based on the coordinates provided.
     *
     * JSON Format : {"longitude":-25.755641,"latitude":28.231901};
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * lat: Latitude of location.
     * lon: Longitude of location.
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON string
     */
    public String getBuildingByCoordinates(String jsonString) // FINISHED
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
            c = DriverManager.getConnection(url, user, password);
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

            System.out.println("Search for building by coordinates done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

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
     * getLocationByRoomNumber(buildingName , room) - gets a location of a room by providing the building name and room.
     *
     * JSON Format : "{"name":"IT Building, "roomName": "IT 4-5"}";
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * Uses a HashMap to link the building name to a table name in the database,
     * along with the class Room to create the JSON objects.
     *
     * buildingName: Building name of location e.g. IT Building.
     * room:         Room number in the form of "IT 4-1" or "EMB 2-151".
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON String
     */
    public String getLocationByRoomNumber(String jsonString) // FINISHED
    {
        // JSON Conversion
        Gson jsonObj = new Gson();
        Gson jsonObjTwo = new Gson();

        Building buildingObj = jsonObj.fromJson(jsonString, Building.class);
        Room roomObj = jsonObjTwo.fromJson(jsonString, Room.class);

        Building b = new Building();

        String buildingNameSearch = buildingObj.getName();
        String room = roomObj.getRoomName();
        String returnJsonString = "";

        String build = b.buildingsMap.get(buildingNameSearch);

        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();

        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
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
                returnJsonString =  jsonObj.toJson(rm);
            }

            rs.close();
            stmt.close();
            c.close();

            System.out.println("Search for building room by name done successfully");
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return returnJsonString;

    }


    /**
     * getRoutes(sLat, sLong, dLat, dLong) - provides all possible routes between a start and end point for a user.
     *
     * JSON Format : {"sLat":"28.00", "sLong": "-20.00" ,"dLat": 28.231901, "dLong":-25.755641};
     *
     * Will receive these parameters in the form of a JSON String - use https://sites.google.com/site/gson/gson-user-guide.
     *
     * sLat:          Start latitude
     * sLong:         Start longitude
     * dLat:          Destination latitude
     * dLong:         Destination longitude
     *
     * @param jsonString JSON String with above parameters
     *
     * @return JSON String array
     */
    public String getRoutes(String jsonString) // DONE
    {
        // Clear Vectors
        Building.buildingVector.clear();
        Room.roomVector.clear();
        String returnJsonString = "";

        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        Gson returnJson = new Gson();

        double sLat = jsonObject.get("sLat").getAsDouble();
        double sLong = jsonObject.get("sLong").getAsDouble();
        double dLat = jsonObject.get("dLat").getAsDouble();
        double dLong = jsonObject.get("dLong").getAsDouble();


        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, password);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "SELECT * FROM PUBLIC.buildings WHERE latitude BETWEEN " + "'" + sLat + "' AND '"+dLat+"'";


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
            System.out.println("Possible routes have been given successfully");


        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        return returnJson.toJson(Building.buildingVector);
    }
}