package GISModule;

public class Room
{
    private int id;
    private String roomName;
    private int level;
    private double longitude;
    private double latitude;
    private int build_id;


    public Room()
    {
        id = -1;
        roomName = "";
        level = -1;
        longitude = 0.0;
        latitude = 0.0;
        build_id = -1;
    }

    public Room(int i, String r, int l, double lon, double lat, int bd)
    {
        id = i;
        roomName = r;
        level = l;
        longitude = lon;
        latitude = lat;
        build_id = bd;
    }

}
