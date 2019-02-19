package Controllers;

import java.util.ArrayList;

public class AdminUser extends User {
  private String accessLevel;

  /**
   * Constructor of the Admin User to access to class Controllers.Administrator information
   * depending on the access level of the Admin User
   */
  public AdminUser(String name, String email, String accessLevel, String password) {
    super(name, email, password);
      this.accessLevel = accessLevel;
  }

    /**
     * Sets a access level of the Controllers.AdminUser
     *
     * @param access the new password to change
     */
    public void setAccessLevel(String access) {
        this.accessLevel = access;
    }

    /**
     * Returns the Access Level of the Controllers.AdminUser
     *
     * @return String the access of the Admin User
     */
    public String getAccessLevel() {
    return this.accessLevel;
  }

  /**
   * Returns the List of Users for all access
   *
   * @return ArrayList<Controllers.TransitUser>
   */
  ArrayList<TransitUser> getTransitUsers() {
    return Administrator.getTransitUsers();
  }

  /**
   * Returns the List of all Cards for all access
   *
   * @return ArrayList<Controllers.TransitCard>
   */
  ArrayList<TransitCard> getListOfCards() {
    return Administrator.getListOfCards();
  }

  /**
   * Returns the List of all Admin Users if access level is high
   *
   * @return ArrayList<Controllers.AdminUser>
   */
  ArrayList<AdminUser> getListOfAdminUser() {
    ArrayList<AdminUser> lst = null;
      if (this.accessLevel.equals("High")) {
      lst = Administrator.getAdminUsers();
    }
    return lst;
  }

  /**
   * Returns the List of all teh days created if access level is high or medium
   *
   * @return ArrayList<Controllers.FareSystem>
   */
  ArrayList<InfoCollector> getFareCollector() {
      ArrayList<InfoCollector> lst = null;
      if (this.accessLevel.equals("High") || this.accessLevel.equals("Medium")) {
          lst = Administrator.getListOfDays();
    }
    return lst;
  }

    /**
     * Returns the total of Stations and Stops reached by all cardholders if access level is high or
   * medium
   *
   * @return int
   */
  int getTotalReached() {
    int totalReached = 0;
      if (this.accessLevel.equals("High") || this.accessLevel.equals("Medium")) {
      for (TransitCard card : Administrator.getListOfCards()) {
        totalReached += card.getStationsReached() + card.getStopsReached();
      }
    }
    return totalReached;
  }

  /**
   * Returns the total of revenue collected so far if access level is high or medium
   *
   * @return double
   */
  double getTotalRevenue() {
    double totalRevenue = 0.0;
      if (this.accessLevel.equals("High") || this.accessLevel.equals("Medium")) {
          for (InfoCollector collector : Administrator.getListOfDays()) {
              totalRevenue += collector.getFareAccumulator();
      }
    }
    return totalRevenue;
  }

  /**
   * Prints the list of all Users in the list
   *
   * @return string
   */
  String printTransitUsers() {
    StringBuilder result = new StringBuilder("\n");
    for (TransitUser user : Administrator.getTransitUsers()) {
        result
                .append("User Name: ")
                .append(user.getName())
                .append(", Email: ")
                .append(user.getEmail())
                .append("\n");
    }
    return result.toString();
  }
}
