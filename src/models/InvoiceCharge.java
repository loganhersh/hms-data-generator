package models;

import java.util.Date;

public class InvoiceCharge {
  public String id;
  public String invoiceId;
  public Date dateApplied;
  public double amount;
  public String reason;

  public InvoiceCharge(String id, String invoiceId, Date dateApplied, double amount,
          String reason) {
    this.id = id;
    this.invoiceId = invoiceId;
    this.dateApplied = dateApplied;
    this.amount = amount;
    this.reason = reason;
  }


}
