import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import models.Guest;
import models.Invoice;
import models.Payment;

public class PaymentData implements Data {

  String tableName = "PAYMENT";
  Random rand = new Random();
  Invoice invoice;
  Guest guest;
  Payment payment;


  @Override
  public String getInsertString() {
    if(invoice.amountPaid == 0) {
      return "";
    }
    payment = new Payment(getId(),invoice.id,getDate(),getType(),getAmount(),
            getName(),getAccountNum(),getMonth(),getYear(),"Mastercard");

    String insertString =
            "INSERT INTO "+tableName+" VALUES ('"+payment.id+"','"+payment.invoiceId+"','"
                    + getDateString(payment.date)+"','"+payment.type+"',"+format(payment.amount);
    if(payment.type.equals("CA")) {
      return insertString+");\n";
    } else {
      return insertString+",'"+ payment.accountHolder+"',"+payment.accountNumber+","+payment.expirationMonth
              + ","+payment.expirationYear+",'"+payment.cardNetwork+"');\n";
    }
  }

  @Override
  public void reset() {
    this.payment = null;
    this.guest = null;
    this.invoice = null;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setGuest(Guest guest) {
    this.guest = guest;
  }

  private String getId() {
    return invoice.id + "-p00001";
  }

  private Date getDate() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DATE, 2);
    return c.getTime();
  }

  private String getType() {
    return (rand.nextInt(100) < 75) ? "CC" : "CA";
  }

  private double getAmount() {
    return invoice.amountPaid;
  }

  private String getName() {
    return guest.firstname + " " + guest.lastname;
  }

  private long getAccountNum() {
    long high = 9999999999999999L;
    long low = 1000000000000000L;
    return low + (long) (Math.random() * (high - low));
  }

  private int getMonth() {
    return rand.nextInt(12)+1;
  }

  private int getYear() {
    return 2021 + (int) (Math.random() * (2028 - 2021));
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
