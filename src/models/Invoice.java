package models;

public class Invoice {
  public String id;
  public double total;
  public double amountPaid;

  public Invoice(String id, double total, double amountPaid) {
    this.id = id;
    this.total = total;
    this.amountPaid = amountPaid;
  }

}
