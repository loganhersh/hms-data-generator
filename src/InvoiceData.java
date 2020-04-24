import java.text.DecimalFormat;
import java.util.Random;
import models.Invoice;
import models.Reservation;

public class InvoiceData implements Data {

  String tableName = "INVOICE";
  Reservation reservation;
  Random rand = new Random();
  Invoice invoice;

  @Override
  public String getInsertString() {
    invoice = new Invoice(reservation.id, getTotal(), getAmountPaid(getTotal()));
    String insertString = "INSERT INTO "+tableName+" VALUES ('"+invoice.id+"',";
    insertString += format(invoice.total)+","+format(invoice.amountPaid)+");\n";
    return insertString;
  }

  @Override
  public void reset() {
    this.invoice = null;
    this.reservation = null;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  private double getTotal() {
    return RoomData.roomtypes.get(reservation.roomtype) * reservation.numDays * 1.06625;
  }

  private double getAmountPaid(double amountDue) {
    int x = rand.nextInt(100);
    if(x < 25) {
      return 0;
    } else if(x < 50) {
      return rand.nextInt((int)amountDue);
    } else {
      return amountDue;
    }
  }

  private String format(double d) {
    DecimalFormat f = new DecimalFormat("#.##");
    return f.format(d);
  }


}
