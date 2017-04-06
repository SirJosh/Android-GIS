package org.gis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Josh on 2017/04/02.
 */
public class DB_Functions
{
//    CREATE TABLE

    public void CreateTable(String tableName)
    {
        Connection c = null;
        Statement stmt = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:10000/navup", "postgres", "password");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE " + tableName +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }


//    INSERT INTO DATABASE

    public void Insert()
    {
        Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/napup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                    + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }


//    SEARCH
    public void SearchByBuildingName(String buildingName)
    {
//        Gson jsonObj = new Gson();
//
//        String jsonString = jsonObj.toJson();

        Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:10000/napup","postgres", "password");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            while ( rs.next() )
            {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary + "\n ");
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
        System.out.println("Operation done successfully");
    }

    public void SearchByCoordinates(double latitude, double longitue)
    {

    }

    public void SearchByRoomNumber(String room)
    {
//        Make sure format is e.g "IT 4-1" or "EMB 3-125"
    }

//     UPDATE
    public void UpdateBuildingName(String buildingName, String newName)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE Buidings " +
                    "SET name = "+newName+"\n" +
                    "WHERE name = "+buildingName+";";


            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Buidings;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                double latitude  = rs.getDouble("age");
                double longitude = rs.getDouble("address");
                int level = rs.getInt("level");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "LATITUDE = " + latitude );
                System.out.println( "LONGITUDE = " + longitude );
                System.out.println( "LEVEL = " + level );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void UpdateBuildingLatitude(String buildingName, double latitude)
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE Buidings " + 
                    "SET latitude = "+latitude+"\n" +
                    "WHERE name ="+buildingName+";";

            stmt.executeUpdate(sql);
            c.commit();
            
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void UpdateBuildingLongitude(String buildingName, double longitude)
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE Buidings " +
                    "SET longitude = "+longitude+"\n" +
                    "WHERE name ="+buildingName+";";

            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void UpdateRoomLevel(String buildingName, String room_name, int level)
    {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE " + buildingName + //The building name is the name of the table
                    "SET level =" +level+"\n" + //Update the level of the room
                    "WHERE room_name ="+room_name+";"; //Search according to room name

            stmt.executeUpdate(sql);
            c.commit();

        
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void UpdateRoomName(String buildingName, String room_name, String newName)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE " + buildingName + //The building name is the name of the table
                    "SET room_name =" +newName+"\n" + //Update the level of the room
                    "WHERE room_name ="+room_name+";"; //Search according to room name


            stmt.executeUpdate(sql);
            c.commit();
            
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }


//    DELETE
    public void DeleteBuilding()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "manisha", "123");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from COMPANY where ID=2;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }


    public void DeleteBuildingRoom()
    {

    }

}

