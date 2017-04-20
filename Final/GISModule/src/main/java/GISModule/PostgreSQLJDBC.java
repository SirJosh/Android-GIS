package GISModule;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Main class for testing our Database functions
 */
public class PostgreSQLJDBC
{
    private static DB_Functions ex = new DB_Functions();

    public static void main(String args[])
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/tempnavup", "postgres", "password");
            System.out.println("First Check: Opened database successfully\n");


            // INSERT FUNCTIONS
            //insertFunctions();

            // LIST & SEARCH FUNCTIONS
            //listSearchFunctions();

            // UPDATES FUNCTIONS
            //updateFunctions();

            // GET ROUTE
            //getRoutes();
            
            // REMOVE FUNCTIONS
            //removeFunctions();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Shows all the insert functions of the database
     */
    private static void insertFunctions()
    {
        // LIST ALL BUILDINGS
        System.out.println("Before Buildings Insert");
        System.out.println(ex.listBuildings() + "\n");

        String insBuilding1 = "{\"name\":\"One Building\",\"longitude\":-24.7556415,\"latitude\":27.2319014,\"description\":\"This is the one buidling\"}";
        String insBuilding2 = "{\"name\":\"Two Building\",\"longitude\":-25.7556415,\"latitude\":28.2319014,\"description\":\"This is the two buidling\"}";
        String insBuilding3 = "{\"name\":\"Three Building\",\"longitude\":-26.7556415,\"latitude\":29.2319014,\"description\":\"This is the three buidling\"}";

        ex.insertBuilding(insBuilding1);
        ex.insertBuilding(insBuilding2);
        ex.insertBuilding(insBuilding3);

        System.out.println("\nAfter Buildings Insert");


        System.out.println("After Insert");
        System.out.println(ex.listBuildings() + "\n");

        System.out.println("IT Room Insert");
        ex.insertBuildingRoom("{\"name\": \"IT Building\", \"roomName\": \"IT 5-100\", \"longitude\":-25.00000,\"latitude\":28.00000, \"level\":5 }");

        System.out.println("Display Rooms");
        System.out.println(ex.listRoomNames("{\"name\": \"IT Building\"}"));

        System.out.println("EMB Room Insert");
        ex.insertBuildingRoom("{\"name\": \"EMB Building\", \"roomName\": \"EB/EMB 6-500\", \"longitude\":-25.12354,\"latitude\":28.1254, \"level\":6 }");

        System.out.println("Display Rooms");
        System.out.println(ex.listRoomNames("{\"name\": \"EMB Building\"}"));


    }

    /**
     * Shows all the list and search functions of the database
     */
    private static void listSearchFunctions()
    {
        // LIST ALL BUILDINGS
        System.out.println("All Building on Hatfield Campus");
        System.out.println(ex.listBuildings() + "\n");

        // SEARCH FOR ROOMS IN IT BUILDING
        System.out.println("All rooms in the IT Building");
        String ITSearch = "{\"id\":1,\"name\":\"IT Building\",\"longitude\":-25.7556415,\"latitude\":28.2319014,\"description\":\"This is the IT buidling\"}";
        System.out.println(ex.listRoomNames(ITSearch) + "\n");

        // SEARCH FOR ROOMS IN EMB BUILDING
        System.out.println("All rooms in the EMB Building");
        String EMBSearch = "{\"name\":\"EMB Building\"}";
        System.out.println(ex.listRoomNames(EMBSearch) + "\n");

        // GET BUILDING BY NAME
        System.out.println("Search For IT Building Details");
        String ITBuildSearch = "{\"name\":\"IT Building\"}";
        System.out.println(ex.getBuildingByName(ITBuildSearch) + "\n");

        System.out.println("Search For EMB Building Details");
        String EMBBuildSearch = "{\"name\":\"EMB Building\"}";
        System.out.println(ex.getBuildingByName(EMBBuildSearch) + "\n");

        System.out.println("Search For Engineering 1 Building Details");
        String EngOneBuildSearch = "{\"name\":\"Engineering 1\"}";
        System.out.println(ex.getBuildingByName(EngOneBuildSearch) + "\n");

        System.out.println("Search For Client Service Centre Building Details");
        String CSCBuildSearch = "{\"name\":\"Client Service Centre\"}";
        System.out.println(ex.getBuildingByName(CSCBuildSearch) + "\n");

        // GET BUILDING BY COORDINATES
        System.out.println("Search For Buildings by ± coordinates (Radius): 1");
        System.out.println(ex.getBuildingByCoordinates("{\"longitude\":-25.755641,\"latitude\":28.231901}") + "\n");
        System.out.println("Search For Buildings by ± coordinates (Radius): 2");
        System.out.println(ex.getBuildingByCoordinates("{\"longitude\":-25.7551,\"latitude\":28.231}") + "\n");
        System.out.println("Search For Buildings by ± coordinates (Radius): 3");
        System.out.println(ex.getBuildingByCoordinates("{\"longitude\":-25.75,\"latitude\":28.231}") + "\n");

        // GET LOCATION BY ROOM NUMBER
        System.out.println("IT 4-1 Search: ");
        System.out.println(ex.getLocationByRoomNumber("{\"name\":\"IT Building\",\"roomName\":\"IT 4-1\"}") + "\n");

        System.out.println("IT 2-25 Search: ");
        System.out.println(ex.getLocationByRoomNumber("{\"name\":\"IT Building\",\"roomName\":\"IT 2-25\"}") + "\n");

        System.out.println("EMB 2-150 Search: ");
        System.out.println(ex.getLocationByRoomNumber("{\"name\":\"EMB Building\",\"roomName\":\"EB/EMB 2-150\"}") + "\n");

        System.out.println("EMB 4-150 Search: ");
        System.out.println(ex.getLocationByRoomNumber("{\"name\":\"EMB Building\",\"roomName\":\"EB/EMB 4-150\"}") + "\n");
    }

    /**
     * Shows all the update functions of the database
     */
    private static void updateFunctions()
    {
//        System.out.println("Update IT Building Name");
//        ex.updateBuildingName("{\"oldBuildingName\": \"IT Building\", \"newBuildingName\": \"Information Technology Building\"}");
//
//        System.out.println(ex.listBuildings());
//
//        System.out.println("Update IT Building Name");
//        ex.updateBuildingName("{\"oldBuildingName\": \"Information Technology Building\", \"newBuildingName\": \"IT Building\"}");
//
//        System.out.println(ex.listBuildings());
//
//        System.out.println("Update EMB Building Name");
//        ex.updateBuildingName("{\"oldBuildingName\": \"EMB Building\", \"newBuildingName\": \"Economics Building\"}");
//        System.out.println(ex.listBuildings());
//
//        System.out.println("Update EMB Building Name");
//        ex.updateBuildingName("{\"oldBuildingName\": \"Economics Building\", \"newBuildingName\": \"EMB Building\"}");
//        System.out.println(ex.listBuildings());
//
//
//        System.out.println("Testing room updates");
//
//        System.out.println("Update EMB Room Building Name");
//        ex.updateBuildingRoom("{\"buildingName\": \"EMB Building\", \"oldRoom\": \"EB/EMB 6-150\", \"newRoom\": \"EB/EMB 2-150\"}");
//
//        String EMBSearch = "{\"name\":\"EMB Building\"}";
//        System.out.println(ex.listRoomNames(EMBSearch));
//
//        ex.updateBuildingCoordinates("{\"name\": \"EMB Building\", \"latitude\": \"17.566\", \"longitude\": \"12.78\"}");
//        System.out.println(ex.listBuildings());
//
//        String ITRoomSearch = "{\"name\":\"IT Building\"}";
//        System.out.println(ex.listRoomNames(ITRoomSearch));

        ex.updateBuildingRoomCoordinates("{\"name\": \"IT Building\", \"roomName\": \"IT 4-5\" ,\"latitude\": \"17.566\", \"longitude\": \"12.78\"}");
        System.out.println(ex.listBuildings());
    }

    /**
     * Shows all the remove functions of the database
     */
    private static void removeFunctions()
    {
        System.out.println("Remove HB building");
        ex.removeBuilding("{\"name\":\"Humanities Building\"}");
        System.out.println("Buildings After Remove");
        System.out.println(ex.listBuildings());


        System.out.println("Remove EB/EMB 2-150 building");
        ex.removeBuildingRoom("{\"name\":\"EMB Building\", \"roomName\":\"EB/EMB 2-150\"}");
        System.out.println("Buildings After Remove");
        String EBBuildSearch = "{\"name\":\"EMB Building\"}";
        System.out.println(ex.getBuildingByName(EBBuildSearch) + "\n");

        System.out.println("Remove IT 4-5 building");
        ex.removeBuildingRoom("{\"name\":\"IT Building\", \"roomName\":\"IT 4-5\"}");
        System.out.println("Buildings After Remove");
        String ITBuildSearch = "{\"name\":\"IT Building\"}";
        System.out.println(ex.getBuildingByName(ITBuildSearch) + "\n");

        System.out.println("Remove EMB building");
        ex.removeBuilding("{\"name\":\"EMB Building\"}");
        System.out.println("Buildings After Remove");
        System.out.println(ex.listBuildings());


    }

    /**
     * Shows locations between two points
     */
    private static void getRoutes()
    {
        System.out.println("Get Route Between Random Point and IT Building");

        String routeOne = "{\"sLong\":-25.7556, \"sLat\":28.2, \"dLong\":-25.7556419, \"dLat\":28.23190}";
        System.out.println(ex.getRoutes(routeOne));
    }
}