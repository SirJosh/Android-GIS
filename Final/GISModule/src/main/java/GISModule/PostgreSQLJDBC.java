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

//            System.out.println(ex.SearchByBuildingName("EMB Building") + "\n");
//            System.out.println(ex.SearchByBuildingByCoordinates(28.2319, -25.7556) + "\n");
//            System.out.println(ex.SearchByRoomNumber("IT Building", "IT 4-5") + "\n");
//            System.out.println(ex.GetAllBuildings());
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