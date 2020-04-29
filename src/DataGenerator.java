import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import models.Guest;
import models.Room;

public class DataGenerator {

    FileWriter writer;
    static String filename = "sample-data.sql";
    static int i;
    String databaseName = "HMS_DB";

    static DataGenerator dataGen;
    static RoomData roomData = new RoomData();
    static GuestData guestData = new GuestData();
    static ReservationData resData = new ReservationData();
    static InvoiceData invoiceData = new InvoiceData();
    static PaymentData paymentData = new PaymentData();
    static InvoiceChargeData invoiceChargeData = new InvoiceChargeData();
    Data[] dataGenArr;

    public static void main(String[] args) {
        dataGen = new DataGenerator(filename);

        // Generate Room data
        dataGen.batchWrite(50, roomData);

        for(i=0; i < 40; i++) {
            dataGen.generateSeries();
        }

        dataGen.close();
    }

    public void generateSeries() {
        dataGen.writeLine("-- ------- SAMPLE "+i+" ----------\n" );
        // generate and get guest
        dataGen.writeLine(guestData.getInsertString());
        Guest guest = guestData.getLastGuest();

        // get room
        Room room = roomData.getRoom();

        // generate reservation
        resData.setLastGuest(guest);
        resData.setLastRoom(room);
        dataGen.writeLine(resData.getInsertString());

        // generate invoice
        invoiceData.setReservation(resData.getReservation());
        String invoiceString = invoiceData.getInsertString();
        // dataGen.writeLine(invoiceData.getInsertString());    // Created through mysql trigger

        // generate payment
        paymentData.setInvoice(invoiceData.getInvoice());
        paymentData.setGuest(guest);
        String paymentString = paymentData.getInsertString();    // write payment after charges

        // generate invoice charges
        if(paymentData.getPayment() != null) {
            invoiceChargeData.setInvoice(invoiceData.getInvoice());
            invoiceChargeData.setReservation(resData.getReservation());
            dataGen.writeLine(invoiceChargeData.getInsertString());

            if(invoiceChargeData.getCharge().amount < invoiceData.getInvoice().total) {
                dataGen.writeLine(invoiceChargeData.getInsertString());
            }
        }

        dataGen.writeLine(paymentString);

        resetData();
        flush();
    }

    public DataGenerator(String filename) {
        File file = createFile(filename);
        writer = getWriter(file);
        dataGenArr = new Data[]{guestData, resData, invoiceData, paymentData, invoiceChargeData};
        writeLine("USE " + databaseName + ";\n\n");
    }

    private void resetData() {
        for(Data d : dataGenArr) {
            d.reset();
        }
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

    private void flush() {
        try {
            writer.flush();
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
