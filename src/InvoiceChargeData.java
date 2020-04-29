import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import models.Invoice;
import models.InvoiceCharge;
import models.Reservation;

public class InvoiceChargeData implements Data {

  String tableName = "INVOICECHARGE";
  Random rand = new Random();
  Invoice invoice;
  String[] reasons = {"Base room charge","Room Service","Cleaning fee"};
  int x = 1;
  InvoiceCharge charge;
  Reservation reservation;

  @Override
  public String getInsertString() {
    charge = new InvoiceCharge(getId(),invoice.id,getDateApplied(),getAmount(),getReason());
    String insertString = "INSERT INTO "+tableName+" VALUES ('"+charge.id+"','"+charge.invoiceId+
            "','"+getDateString(charge.dateApplied)+"',"+format(charge.amount)+","
            + "'"+charge.reason+"');\n";
    return insertString;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public void setReservation(Reservation r) { this.reservation = r; }

  public InvoiceCharge getCharge() {
    return charge;
  }

  private Date getDateApplied() {
    Calendar c = Calendar.getInstance();
    c.setTime(reservation.checkIn);
    return c.getTime();
  }

  private String getId() {
    return invoice.id + "-ch0000" + x++;
  }

  public void reset() {
    this.charge = null;
    this.x = 1;
  }

  private double getAmount() {
    if(charge != null) {
      return getSubsequentAmount();
    }
    if(rand.nextInt(100) > 50) {
      return invoice.total;
    } else {
      return invoice.total * .66;
    }
  }

  private double getSubsequentAmount() {
    return invoice.total - charge.amount;
  }

  private String getReason() {
    if(charge == null) {
      return reasons[0];
    } else {
      return (rand.nextInt(100) < 50 ? reasons[1] : reasons[2]);
    }
  }

  String getDateString(Date date) {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    return f.format(date);
  }

  private String format(double d) {
    DecimalFormat f = new DecimalFormat("#.##");
    return f.format(d);
  }
}
