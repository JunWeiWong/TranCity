package Controllers;

import java.util.ArrayList;
import java.util.Date;

public class TransitUser extends User {
  private ArrayList<TransitCard> cards;
  private long initiationDate;
  private long dateOfBirth;

  /**
   * Constructor of a transit card holder, which contains the holder's name, email, all transit
   * cards named under the holder's email, and the date of the holder applied for the transit
   * service.
   *
   * @param name           the card holder's name
   * @param email          the email of the card holder
   * @param initiationDate the date of the holder applied for the transit service
   */
  public TransitUser(
          String name, String email, long initiationDate, String password, long dateOfBirth) {
    super(name, email, password);
    this.cards = new ArrayList<>();
    this.initiationDate = initiationDate;
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Adds a new transit card to the list of all transit cards named under the card holder.
   *
   * @param newCard the added new transit card
   */
  public void addCard(TransitCard newCard) {
    this.cards.add(newCard);
  }

  /**
   * Removes a transit card from the list of all transit cards named under the card holder.
   *
   * @param card the removed card number of the transit card
   */
  public void removeCard(TransitCard card) {
    cards.remove(card);
  }

  /**
   * Transfer a part of amount of a transit card to another.
   *
   * @param card1   the card number of the transferred transit card
   * @param card2   the card number of the received transit card
   * @param balance the transferred amount of credit
   */
  public void transferBalance(int card1, int card2, double balance) {

    TransitCard c1 = getCardByNumber(card1);
    TransitCard c2 = getCardByNumber(card2);
    if (c1.getBalance() >= balance) {
      if (balance != 0.0) {
        c1.setBalance(c1.getBalance() - balance);
        c2.setBalance(c2.getBalance() + balance);
      } else {
        double amount = c1.getBalance();
        c1.setBalance(0.0);
        c2.setBalance(c2.getBalance() + amount);
      }
    }
  }

  /**
   * Returns the three recent trips, which are paid with the transit card
   *
   * @return String
   */
  // Return an ArrayList containing three most recent trips if any.
  public String[][] getThreeRecentTrips() {
    ArrayList<Trip> recentTrips = new ArrayList<>();
    ArrayList<Trip> allTrips = new ArrayList<>();
    for (TransitCard card : this.cards) {
      allTrips.addAll(card.getListOfTrips());
    }
    while (recentTrips.size() < 15 && allTrips.size() > 0) {
      Trip latestTrip = allTrips.get(0);
      for (int i = 1; i < allTrips.size(); i++) {
        if (latestTrip.getEndTime() < allTrips.get(i).getEndTime()) {
          latestTrip = allTrips.get(i);
        }
      }
      allTrips.remove(latestTrip);
      recentTrips.add(latestTrip);
    }
    // System.out.println("These are the trips");
    String[][] output = new String[15][4];
    for (int i = 0; i < recentTrips.size(); i++) {
      String[] parts = recentTrips.get(i).toString().split(",");
      output[i][0] = parts[0];
      output[i][1] = parts[1];
      output[i][2] = parts[2];
      output[i][3] = parts[3];
    }
    return output;
  }

  /**
   * Returns the average cost per month of the card holder when he first time applied a transit
   * card.
   *
   * @param currentTime the end time of the calculated duration
   * @return double
   */
  public double getAverageCostPerMonth(long currentTime) {
    long diffInMilliSecond = (currentTime - this.initiationDate);
    double minutes = (diffInMilliSecond / (1000 * 60));
    double hours = minutes / 60;
    double days = hours / 24;
    double months = days / 30;
    double totalCost = 0;
    for (TransitCard card : cards) {
      totalCost += card.getAddedAmount() - card.getBalance();
    }
    if (months < 1) {
      return totalCost;
    }
    return totalCost / months;
  }

  /**
   * Returns the registered date of the user.
   *
   * @return long
   */
  public long getInitiationDate() {
    return this.initiationDate;
  }

  /**
   * Returns a list of all cards of this user.
   *
   * @return ArrayList<TransitCard>
   */
  public ArrayList<TransitCard> getCards() {
    return this.cards;
  }

  /**
   * Returns the transit card with a certain card number.
   *
   * @param num the card number
   * @return TransitCard
   */
  TransitCard getCardByNumber(int num) {
    TransitCard result = null;
    for (TransitCard card : cards) {
      if (card.getCardNumber() == num) result = card;
    }
    return result;
  }

  /**
   * Returns a String list of all card numbers.
   *
   * @return String[]
   */
  public String[] getArrayOfCards() {
    String[] result = new String[cards.size()];
    for (int i = 0; i < cards.size(); i++) {
      result[i] = String.valueOf(cards.get(i).getCardNumber());
    }
    return result;
  }

  String getAgeGroup(long currentDate) {
    long diffInMilliSecond = (currentDate - this.dateOfBirth);
    double minutes = (diffInMilliSecond / (1000 * 60));
    double hours = minutes / 60;
    double days = hours / 24;
    double months = days / 30;
    double years = months / 12;
    if (years < 20) {
      return "Youth";
    } else if (years < 65) {
      return "Adult";
    } else {
      return "Senior";
    }
  }

  public String getDateOfBirth() {
    String date = new Date(dateOfBirth).toString();
    String[] parts = date.split(" ");
    String result = parts[1] + ", " + parts[2] + ", " + parts[5];
    return result;
  }
}
