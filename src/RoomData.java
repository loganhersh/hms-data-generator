import java.util.Random;

public class RoomData implements Data{

    String tableName = "room";
    Random rand = new Random();
    String[] col3Vals = {"KI","QD","KS","QS","KJ"};

    public String getInsertString() {
        return "INSERT INTO " + tableName + " VALUES (" + getCol1Val() + "," + getCol2Val() + ","
                        + "'" + getCol3Val() + "');\n";
    }

    int getCol1Val() {
        return rand.nextInt(1000);
    }

    boolean getCol2Val() {
        int x = rand.nextInt(100);
        return (x > 25);
    }

    String getCol3Val() {
        return col3Vals[rand.nextInt(5)];
    }
}
