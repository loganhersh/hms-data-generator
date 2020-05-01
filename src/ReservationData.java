import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import models.Guest;
import models.Reservation;
import models.Room;

public class ReservationData implements Data {

  String tableName = "RESERVATION";
  Random rand = new Random();
  Reservation res;
  Guest lastGuest;
  Room lastRoom;

  @Override
  public String getInsertString() {
    buildReservation();
    String insertString = "INSERT INTO "+tableName+" VALUES ('"+res.id+"',";
    insertString += res.guestId+","+res.roomId+",'"+res.roomtype+"','";
    insertString += getDateString(res.checkIn)+"','"+getDateString(res.checkOut)+"',null,'"+res.status+"');\n";
    return insertString;
  }

  @Override
  public void reset() {
    this.lastRoom = null;
    this.lastGuest = null;
    this.res = null;
  }

  public void setLastGuest(Guest guest) {
    this.lastGuest = guest;
  }

  public void setLastRoom(Room room) {
    this.lastRoom = room;
  }

  public Reservation getReservation() {
    return res;
  }

  void buildReservation() {

    int guestId = lastGuest.id;
    Date checkIn = getCheckInDate();
    Date checkOut = getCheckOutDate(checkIn);
    String roomtype = lastRoom.type;
    String id = getIdString(checkIn);
    res = new Reservation(id, guestId, lastRoom.id, roomtype, checkIn, checkOut);
  }

  String getIdString(Date date) {
    String id1 = getIdDateString(date);
    long highLimit = 9999999999999L;
    long lowLimit = 1000000000000L;
    long id2 = lowLimit + (long) (Math.random() * (highLimit - lowLimit));
    return id1 + lastRoom.type + id2;
  }

  Date getCheckInDate() {
    Date current = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(current);
    c.add(Calendar.DATE, rand.nextInt(365)+20);
    return c.getTime();
  }

  Date getCheckOutDate(Date checkInDate) {
    Calendar c = Calendar.getInstance();
    c.setTime(checkInDate);
    c.add(Calendar.DATE, rand.nextInt(3)+1);
    return c.getTime();
  }

  String getDateString(Date date) {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    return f.format(date);
  }

  String getIdDateString(Date date) {
    SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
    return f.format(date);
  }



}
