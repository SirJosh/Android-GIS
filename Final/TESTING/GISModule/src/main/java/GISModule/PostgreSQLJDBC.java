package GISModule;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Main class for testing our Database functions
 */
public class PostgreSQLJDBC
{
    public static void main(String args[])
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup", "postgres", "password");
            System.out.println("First Check: Opened database successfully\n");

            DB_Functions ex = new DB_Functions();


            // LIST ALL BUILDINGS
            System.out.println(ex.listBuildings() + "\n");

            // SEARCH FOR ROOMS IN BUILDING
            String ITSearch = "{\"id\":1,\"name\":\"IT Building\",\"longitude\":-25.7556415,\"latitude\":28.2319014,\"description\":\"This is the IT buidling\"}";
            System.out.println(ex.listRoomNames(ITSearch) + "\n");

            // GET BUILDING BY NAME
            String ITBuildSearch = "{\"name\":\"IT Building\"}";
            System.out.println(ex.getBuildingByName(ITBuildSearch) + "\n");

            // GET BUILDING BY COORDINATES
            System.out.println(ex.getBuildingByCoordinates("{\"longitude\":-25.755641,\"latitude\":28.231901}") + "\n");

            // GET LOCATION BY ROOM NUMBER
            System.out.println(ex.getLocationByRoomNumber("{\"name\":\"IT Building\",\"roomName\":\"IT 4-1\"}") + "\n");

            //ex.insertBuildingRoom("{\"name\": \"IT Building\", \"roomName\": \"IT 5-100\", \"longitude\":-25.00000,\"latitude\":28.00000, \"level\":5 }");

            //ex.insertBuilding("{\"name\": \"Joshua Land Building\", \"description\": \"This is mine now\", \"longitude\":-25.00000,\"latitude\":28.00000}");
            //System.out.println(ex.listRoomNames(ITSearch) + "\n");

            // LIST ALL BUILDINGS
           // System.out.println(ex.listBuildings() + "\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}