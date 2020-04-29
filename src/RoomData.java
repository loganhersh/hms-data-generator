import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import models.Room;

public class RoomData implements Data{

    String tableName = "ROOM";
    Random rand = new Random();
    String[] types = {"KI","QD","KS","QS","KJ"};
    Double[] roomPrices = {79.99,89.99,99.99,109.99,129.99};
    static HashMap<String, Double> roomtypes = new HashMap<>();
    int index = 0;
    ArrayList<Room> rooms = new ArrayList<>();

    public RoomData() {
        for(int i=0; i<types.length; i++){
            roomtypes.put(types[i],roomPrices[i]);
        }
    }

    public String getInsertString() {
        Room room = new Room(getId(), getInService(), getType());
        rooms.add(room);
        return "INSERT INTO " + tableName + " VALUES (" + room.id + "," + room.inService + ","
                        + "'" + room.type + "');\n";
    }

    @Override
    public void reset() {}

    int getId() {
        int id = rand.nextInt(1000);
        while(roomExists(id)) {
            id = rand.nextInt(1000);
        }
        return id;
    }

    boolean getInService() {
        int x = rand.nextInt(100);
        return (x > 25);
    }

    String getType() {
        return types[rand.nextInt(5)];
    }

    public Room getRoom() {
        return rooms.get(rand.nextInt(rooms.size()));
    }

    private boolean roomExists(int id) {
        for(Room r : rooms) {
            if(r.id == id) return true;
        }
        return false;
    }
}
