import java.util.Random;

public class GuestData implements Data {

    String tableName = "GUEST";
    NameGenerator nameGen;
    Random rand = new Random();
    int id = 1000;
    String[] emailDomains = {"gmail.com","hotmail.com","yahoo.com"};
    String street = "1234 DaWrong Lane";
    String city = "Flonkerton";
    String state = "MD";
    String zip = "12345";
    String[] lastGuest = new String[3];

    @Override
    public String getInsertString() {
        String insertString = "INSERT INTO "+tableName+" VALUES("+ (id++) +",'";
        String[] name = nameGen.getName();
        insertString += name[0]+"','"+name[1]+"','";
        insertString += getEmail(name[0],name[1]) + "','";
        insertString += street+"','"+city+"','"+state+"',"+zip+");";



        return insertString;
    }

    public GuestData() {
        nameGen = new NameGenerator();
    }

    private String getEmail(String first, String last) {
        return first.charAt(0) + last + "@" + emailDomains[rand.nextInt(emailDomains.length)];
    }

}
