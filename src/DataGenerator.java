import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

    FileWriter writer;
    String filename = "guest-data.sql";

    public static void main(String[] args) {
        GuestData guestData = new GuestData();
        DataGenerator dataGen = new DataGenerator();
//        dataGen.batchWrite(50, roomData);
//        dataGen.close();
    }

    public DataGenerator() {
        File file = createFile(filename);
        writer = getWriter(file);
    }

    private void batchWrite(int cycles, Data data) {
        for(int i=0; i<cycles; i++) {
            writeLine(data.getInsertString());
        }
    }

    private void writeLine(String line) {
        try {
            writer.append(line);
        } catch(IOException e) {
            System.out.println("Write failed");
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            writer.close();
        } catch(IOException e) {
            System.out.println("Failed to close");
            e.printStackTrace();
        }
    }

    private File createFile(String filename) {
        try {
            File outputFile = new File(filename);
            if(outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getAbsolutePath());
            } else {
                System.out.println("File " + filename + " already exists");
            }
            return outputFile;
        } catch(IOException e) {
            System.out.println("File failed");
            e.printStackTrace();
        }
        return null;
    }

    private FileWriter getWriter(File file) {
        try {
            return new FileWriter(file);
        } catch(IOException e) {
            System.out.println("Writer failed");
            e.printStackTrace();
        }
        return null;
    }

}
