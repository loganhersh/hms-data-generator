package models;

public class Room {
  public int id;
  public boolean inService;
  public String type;

  public Room(int id, boolean inService, String type) {
    this.id = id;
    this.inService = inService;
    this.type = type;
  }
}
