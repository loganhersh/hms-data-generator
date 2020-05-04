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
    static int lastRoomTypeIndex = 0;
    static int lastRoomId = 100;
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
                        + "'" + room.type + "',"+room.isVacant+");\n";
    }

    @Override
    public void reset() {}

    int getId() {
        if(lastRoomId == 120) {
            lastRoomId = 200;
        } else {
            lastRoomId++;
        }
        return lastRoomId;
    }

    boolean getInService() {
        int x = rand.nextInt(100);
        return (x > 25);
    }

    String getType() {
        return types[getNextIndex()];
    }

    int getNextIndex() {
        if(lastRoomTypeIndex == types.length-1) {
            lastRoomTypeIndex = 0;
            return 0;
        } else {
            lastRoomTypeIndex++;
            return lastRoomTypeIndex;
        }
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
