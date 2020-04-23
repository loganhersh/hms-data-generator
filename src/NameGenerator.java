import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator {

    String namesFile = "names.txt";
    ArrayList<String> firstnames = new ArrayList<>();
    ArrayList<String> lastnames = new ArrayList<>();
    Random rand = new Random();

    public NameGenerator() {
        loadNames(namesFile);
    }

    String[] getName() {
        int index = rand.nextInt(firstnames.size());
        return new String[]{firstnames.get(index), lastnames.get(index)};
    }

    private void loadNames(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                firstnames.add(sc.next());
                if (sc.hasNext())
                    lastnames.add(sc.next());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
