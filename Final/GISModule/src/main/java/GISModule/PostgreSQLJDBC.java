package GISModule;

import java.sql.Connection;
import java.sql.DriverManager;

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
            String ITBuildSearch = "{\"name\":\"Engineering 1\"}";
            System.out.println(ex.getBuildingByName(ITBuildSearch));

            // GET BUILDING BY COORDINATES
            System.out.println(ex.getBuildingByCoordinates("{\"longitude\":-25.755641,\"latitude\":28.231901}") + "\n");

//            System.out.println(ex.SearchByRoomNumber("IT Building", "IT 4-5") + "\n");
//            System.out.println(ex.GetAllRoomsInBuilding("EMB Building"));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}