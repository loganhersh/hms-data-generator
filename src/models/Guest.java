package models;

public class Guest {
  public int id;
  public String firstname;
  public String lastname;
  public String email;
  public String street = "1234 DaWrong Lane";
  public String city = "Flonkerton";
  public String state = "MD";
  public String zip = "12345";
  public String phone;

  public Guest(int id, String firstname, String lastname, String email, String phone) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
  }
}
