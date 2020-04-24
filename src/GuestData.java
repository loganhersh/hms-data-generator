import java.util.Random;
import models.Guest;

public class GuestData implements Data {

    String tableName = "GUEST";
    NameGenerator nameGen;
    Random rand = new Random();
    int id = 1000;
    String[] emailDomains = {"gmail.com","hotmail.com","yahoo.com"};
    Guest lastGuest;

    @Override
    public String getInsertString() {
        String[] name = nameGen.getName();
        lastGuest = new Guest(id++, name[0], name[1], getEmail(name[0],name[1]));

        String insertString = "INSERT INTO "+tableName+" VALUES("+ lastGuest.id +",'";
        insertString += lastGuest.firstname+"','"+lastGuest.lastname+"','";
        insertString += lastGuest.email + "','";
        insertString += lastGuest.street+"','"+lastGuest.city+"','"+lastGuest.state+"',"+lastGuest.zip+");\n";

        return insertString;
    }

    @Override
    public void reset() {
        if(this.lastGuest != null)
        this.lastGuest = null;
    }

    public Guest getLastGuest() {
        return lastGuest;
    }

    public GuestData() {
        nameGen = new NameGenerator();
    }

    private String getEmail(String first, String last) {
        return first.charAt(0) + last + "@" + emailDomains[rand.nextInt(emailDomains.length)];
    }

}
