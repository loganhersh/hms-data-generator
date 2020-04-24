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
        lastGuest = new Guest(id++, name[0], name[1], getEmail(name[0],name[1]), getPhone());

        String insertString = "INSERT INTO "+tableName+" VALUES("+ lastGuest.id +",'";
        insertString += lastGuest.firstname+"','"+lastGuest.lastname+"','";
        insertString += lastGuest.email + "','";
        insertString += lastGuest.street+"','"+lastGuest.city+"','"+lastGuest.state+"','"+lastGuest.zip
                + "','"+lastGuest.phone+"');\n";

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

    private String getPhone() {
        String phone = "";
        phone += fillPrecedingZeros(3, String.valueOf(rand.nextInt(1000)));
        phone += "-" + fillPrecedingZeros(3, String.valueOf(rand.nextInt(1000)));
        phone += "-" + fillPrecedingZeros(4, String.valueOf(rand.nextInt(10000)));
        return phone;
    }

    private String fillPrecedingZeros(int desiredStringLength, String num) {
        String zeros = "";
        for(int i=0; i<desiredStringLength-num.length(); i++) {
            zeros += "0";
        }
        return zeros + num;
    }

}
