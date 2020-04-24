package models;

import java.util.Date;

public class Payment {
  public String id;
  public String invoiceId;
  public Date date;
  public String type;
  public double amount;
  public String accountHolder;
  public String accountNumber;
  public int expirationMonth;
  public int expirationYear;
  public int cvv;
  public String cardNetwork;

  public Payment(String id, String invoiceId, Date date, String type, double amount,
          String accountHolder, String accountNumber, int expirationMonth, int expirationYear,
          int cvv, String cardNetwork) {
    this.id = id;
    this.invoiceId = invoiceId;
    this.date = date;
    this.type = type;
    this.amount = amount;
    this.accountHolder = accountHolder;
    this.accountNumber = accountNumber;
    this.expirationMonth = expirationMonth;
    this.expirationYear = expirationYear;
    this.cvv = cvv;
    this.cardNetwork = cardNetwork;
  }
}
