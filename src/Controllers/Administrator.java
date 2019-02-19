package Controllers;

import java.util.ArrayList;
import java.util.Date;

public class Administrator {
  private static ArrayList<TransitUser> listOfTransitUsers = new ArrayList<>();
  private static ArrayList<AdminUser> listOfAdminUsers = new ArrayList<>();
  private static ArrayList<TransitCard> listOfCards = new ArrayList<>();
  private static ArrayList<InfoCollector> listOfDays = new ArrayList<>();
  private static final String OPERATINGCOSTPERDAY = "20,000.00";

  /**
   * Adds the object entered to the list depending on the type
   *
   * @param obj the object to be stored
   */
  public static void add(Object obj) {
    if (obj instanceof TransitCard) {
      listOfCards.add((TransitCard) obj);
    } else if (obj instanceof TransitUser) {
      listOfTransitUsers.add((TransitUser) obj);
    } else if (obj instanceof AdminUser) {
      listOfAdminUsers.add((AdminUser) obj);
    } else if (obj instanceof InfoCollector) {
      if (listOfDays.size()
              < 3650) { // If the number of days exceeds 10 years, we start removing info
        listOfDays.add((InfoCollector) obj);
      } else {
        listOfDays.remove(0);
        listOfDays.add((InfoCollector) obj);
      }
    }
  }

  /**
   * Returns the List of Users
   *
   * @return ArrayList<TransitUser>
   */
  public static ArrayList<TransitUser> getTransitUsers() {
    return listOfTransitUsers;
  }

  /**
   * Returns the List of Admin Users
   *
   * @return ArrayList<AdminUser>
   */
  static ArrayList<AdminUser> getAdminUsers() {
    return listOfAdminUsers;
  }

  /**
   * Returns the List of all Cards (active and inactive)
   *
   * @return ArrayList<TransitCard>
   */
  public static ArrayList<TransitCard> getListOfCards() {
    return listOfCards;
  }

  /**
   * Returns the List of all teh days created
   *
   * @return ArrayList<FareSystem>
   */
  public static ArrayList<InfoCollector> getListOfDays() {
    return listOfDays;
  }

  /**
   * Returns the Admin User on the List by the email provided
   *
   * @return AdminUser
   */
  public static AdminUser getAdminUserByEmail(String email) {
    AdminUser result = null;
    for (AdminUser user : listOfAdminUsers) {
      if (user.getEmail().equals(email)) {
        result = user;
      }
    }
    return result;
  }

  /**
   * Returns the Admin User on the List by the name
   *
   * @return AdminUser
   */
  public static AdminUser getAdminUserByName(String name) {
    AdminUser result = null;
    for (AdminUser user : listOfAdminUsers) {
      if (user.getName().equals(name)) {
        result = user;
      }
    }
    return result;
  }

  /**
   * Returns the Transit User on the List by Email
   *
   * @return TransitUser
   */
  public static TransitUser getTransitUserByEmail(String email) {
    TransitUser result = null;
    for (TransitUser user : listOfTransitUsers) {
      if (user.getEmail().equals(email)) {
        result = user;
      }
    }
    return result;
  }

  /**
   * Returns the Transit User on the List by Name
   *
   * @return TransitUser
   */
  public static TransitUser getTransitUserByName(String name) {
    TransitUser result = null;
    for (TransitUser user : listOfTransitUsers) {
      if (user.getName().equals(name)) {
        result = user;
      }
    }
    return result;
  }

  /**
   * Returns the total of Stations and Stops reached by all cardholders
   *
   * @return int
   */
  public static int getTotalReached() {
    int totalReached = 0;
    for (TransitCard card : listOfCards) {
      totalReached += card.getStationsReached() + card.getStopsReached();
    }
    return totalReached;
  }

  /**
   * Returns the total of revenue collected so far
   *
   * @return double
   */
  public static double getTotalRevenue() {
    double totalRevenue = 0.0;
    for (InfoCollector fare : listOfDays) {
      totalRevenue += fare.getFareAccumulator();
    }
    return totalRevenue;
  }

  /**
   * Returns a String list of all Admin users.
   *
   * @return String[]
   */
  public static String[] getArrayOfAdminUsers() {
    String[] result = new String[listOfAdminUsers.size()];
    for (int i = 0; i < listOfAdminUsers.size(); i++) {
      result[i] = listOfAdminUsers.get(i).getName();
    }
    return result;
  }

  /**
   * Returns a String list of all transit users.
   *
   * @return String[]
   */
  public static String[] getArrayOfTransitUsers() {
    String[] result = new String[listOfTransitUsers.size()];
    for (int i = 0; i < listOfTransitUsers.size(); i++) {
      result[i] = listOfTransitUsers.get(i).getName();
    }
    return result;
  }

  /**
   * Returns a String list of all transit cards.
   *
   * @return String[]
   */
  public static String[] getArrayOfCards() {
    String[] result = new String[listOfCards.size()];
    for (int i = 0; i < listOfCards.size(); i++) {
      result[i] = String.valueOf(listOfCards.get(i).getCardNumber());
    }
    return result;
  }

  /**
   * Returns the transit user who has the transit card with a certain card number.
   *
   * @param num the card number
   * @return TransitUser
   */
  public static TransitUser getCardholderByCardNumber(int num) {
    TransitUser result = null;
    for (TransitUser user : listOfTransitUsers) {
      for (TransitCard card : user.getCards()) {
        if (card.getCardNumber() == num) result = user;
      }
    }
    return result;
  }

  /**
   * Returns the transit card with a certain card number.
   *
   * @param num the transit card number
   * @return TransitCard
   */
  public static TransitCard getCardByNumber(int num) {
    TransitCard result = null;
    for (TransitCard card : listOfCards) {
      if (card.getCardNumber() == num) result = card;
    }
    return result;
  }

  /**
   * Returns a String list of all the info of collected fare.
   *
   * @return String[][]
   */
  public static String[][] getNestedOfFareCollectors() {
    String[][] result = new String[getListOfDays().size()][5];
    for (int i = 0; i < getListOfDays().size(); i++) {
      result[i][0] = getListOfDays().get(i).getDate();
      result[i][1] = String.valueOf(getListOfDays().get(i).getFareAccumulator());
      result[i][2] = String.valueOf(getListOfDays().get(i).getReached());
      result[i][3] = String.valueOf(getListOfDays().get(i).getDistance());
      result[i][4] = String.valueOf(getListOfDays().get(i).getPassengers());
    }
    return result;
  }

  /**
   * Returns the daily cost of the entire transit system.
   *
   * @return String
   */
  public static String getOperatingCostPerDay() {
    return OPERATINGCOSTPERDAY;
  }

  public static int[] getAgeGroupReport() {
    String date = listOfDays.get(Administrator.getListOfDays().size() - 1).getDate();
    Date cd = new Date(date);
    int[] result = {0, 0, 0};
    for (TransitUser user : listOfTransitUsers) {
      String g = user.getAgeGroup(cd.getTime());
      if (g.equals("Youth")) {
        result[0] = result[0] + 1;
      } else if (g.equals("Adult")) {
        result[1] = result[1] + 1;
      } else if (g.equals("Senior")) {
        result[2] = result[2] + 1;
      }
    }
    return result;
  }
}
