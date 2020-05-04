package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
  public String id;
  public int guestId;
  public String roomId;
  public String roomtype;
  public Date checkIn;
  public Date checkOut;
  public int numDays;
  public String status;

  public Reservation(String id, int guestId, String roomId, String roomtype, Date checkIn,
          Date checkOut, String status) {
    this.id = id;
    this.guestId = guestId;
    this.roomId = roomId;
    this.roomtype = roomtype;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.numDays = (int) getDateDiff(checkIn, checkOut, TimeUnit.DAYS);
    this.status = status;
  }

  public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
    long diffInMillies = date2.getTime() - date1.getTime();
    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
  }
}
