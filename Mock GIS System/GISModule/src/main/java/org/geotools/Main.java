package org.geotools;

import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) throws Exception
    {
        GISModule geo = new GISModule();
        geo.hardCode();

        Scanner input = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        Scanner HC = new Scanner(System.in);
        String search = "";

        while(true)
        {
            System.out.println("\nSelect an option for demonstration purposes:");
            System.out.println("____________________________________________");
            System.out.println("1. Create Location");
            System.out.println("2. Remove Location");
            System.out.println("3. Update Location");
            System.out.println("4. Display All Possible Way Points");
            System.out.println("5. Display A Specific Building by Coordinates");
            System.out.println("6. Display Coordinates by Specific Building");
            System.out.println("7. Display Map");
            System.out.println("8. EXIT");

            int option = input.nextInt();

            switch (option)
            {
                case 1:
                {
                    System.out.println("Create location");
                    System.out.println("____________________________________________");
                    System.out.print("Building Name: ");
                    String b = input.next();

                    System.out.print("Longitude: ");
                    Double lon = input.nextDouble();

                    System.out.print("Latitude: ");
                    Double lat = input.nextDouble();

                    System.out.print("Altitude: ");
                    Double alt = input.nextDouble();

                    geo.insertLocation(b,lon,lat, alt);
                    break;
                }
                case 2:
                {
                    System.out.println("Remove location");
                    System.out.println("____________________________________________");
                    System.out.println("Enter The Building Name: ");
                    search = HC.nextLine();
                    geo.removeLocation(search);

                    System.out.println("\nRemaining Buildings");
                    System.out.println("____________________________________________");
                    System.out.println(geo.getPossibleWayPoints());
                    break;
                }
                case 3:
                {
                    System.out.println("Update location");
                    System.out.println("____________________________________________");
                    System.out.println("Enter The Building Name: ");
                    search = HC.nextLine();

                    System.out.println("1) Update Building Name, 2) Longitude, 3) Latitude, or 4) Altitude");
                    int i = s.nextInt();

                    if(i == 1)
                    {
                        System.out.println("Enter New Building Name: ");
                        search = HC.nextLine();
                        geo.updateLocationName(search);
                    }
                    else if(i == 2)
                    {
                        System.out.println("Enter The New Longitude Value: ");
                        search = HC.nextLine();
                        geo.updateLocationLong(search);
                    }
                    else if( i == 3)
                    {
                        System.out.println("Enter The New Latitude Value: ");
                        search = HC.nextLine();
                        geo.updateLocationLat(search);
                    }
                    else if( i == 4)
                    {
                        System.out.println("Enter The New Altitude Value: ");
                        search = HC.nextLine();
                        geo.updateLocationAlt(search);
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Display All Possible Way Points");
                    System.out.println("____________________________________________");
                    System.out.println(geo.getPossibleWayPoints());

                    break;
                }
                case 5:
                {
                    System.out.println("Display A Specific Building by Coordinates");
                    System.out.println("____________________________________________");
                    System.out.println("Enter The Longitude: ");
                    double lon = HC.nextDouble();

                    System.out.println("Enter The Latitude: ");
                    double lat = HC.nextDouble();

                    System.out.println("Enter The Altitude: ");
                    double alt = HC.nextDouble();

                    System.out.println(geo.getLocationByCo(lon, lat, alt));

                    break;
                }
                case 6:
                {
                    System.out.println(" Display Coordinates by Specific Building");
                    System.out.println("____________________________________________");
                    System.out.println("Enter The Building Name: ");
                    search = HC.nextLine();

                    System.out.println(geo.getLocationByName(search));

                    break;
                }
                case 7:
                {
                    System.out.println("Display Map");
                    System.out.println(geo.Map());
                    break;
                }
                case 8:
                {
                    System.out.println("Exiting...");
                    return;
                }
                case 9:
                {
                    geo.locations.removeAll(geo.locations);
                    geo.hardCode();
                    break;
                }
                default:
                {
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
    }
}
