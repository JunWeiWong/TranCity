package Controllers;

import Model.*;

import java.util.ArrayList;

public class TransitCard {
    private int cardNumber;
    private double balance;
    private double addedAmount;
    private boolean active;
    private ArrayList<Trip> listOfTrips;
    private int stationsReached = 0;
    private int stopsReached = 0;
    private boolean checkedIn;

    /**
     * Constructor of a transit card, with cardNumber as its card number and credit as additional credit to be added
     * to the starting balance of $19.
     *
     * @param cardNumber The card number of the transit card
     * @param credit Additional credit to be added to the transit card
     */
    public TransitCard(int cardNumber, double credit) {
        this.cardNumber = cardNumber;
        this.balance = 19 + credit;
        this.addedAmount = 19 + credit;
        this.active = true;
        this.listOfTrips = new ArrayList<>();
    }

    /**
     * Add credit to the transit card.
     *
     * @param credit Amount to be added to the transit card
     * @throws IllegalArgumentException Throws exception when illegal argument is used
     */
    public void addCredit(double credit) throws IllegalArgumentException {
        this.balance += credit;
        this.addedAmount += credit;
    }

    /**
     * Using the card to perform the action of tapping into a subway station at subway station station on subway line
     * line with time as tapInTime
     *
     * @param station The name of the subway station
     * @param tapInTime The time when the tapping action is performed
     * @param line The subway line where the subway station belongs
     */
    public void tapInSubway(String station, long tapInTime, String line) {
        SubwayStation startStation = SubwaySystem.getStationByName(line, station);
        SubwayTrip newSubwayTrip = new SubwayTrip(startStation, tapInTime);
        if (this.hasTrip()) {
            Trip lastTrip = this.getLastTrip();
            if (this.isCheckedIn()) {
                lastTrip.removeLastTransitTrip();
                if (!lastTrip.hasTransitTrip()) {
                    this.removeLastTrip();
                }
            }
            if (this.hasTrip()) {
                lastTrip = this.getLastTrip();
                TransitTrip lastTransitTrip = lastTrip.getLastTransitTrip();
                if (lastTrip.hasEnded(tapInTime) || lastTransitTrip.isDisjointed(startStation)) {
                    lastTrip.setEndTime(lastTrip.getTrips().get(lastTrip.getTrips().size() - 1).getEndTime());
                    this.addNewTripForSubwayTrip(newSubwayTrip);
                } else {
                    lastTrip.addTransitTrip(newSubwayTrip);
                }
            } else {
                this.addNewTripForSubwayTrip(newSubwayTrip);
            }
        } else {
            this.addNewTripForSubwayTrip(newSubwayTrip);
        }
        this.checkIn();
    }

    /**
     * Using the card to perform the action of tapping into a bus at bus stop stop on bus route route with time as
     * tapInTime
     *
     * @param stop The stop number of the stop where the tapping action is performed
     * @param tapInTime The time when the tapping action is performed
     * @param route The bus route where the bus stop belongs
     */
    public void tapInBus(int stop, long tapInTime, String route) {
        BusStop startLocation = BusSystem.getStopByNumber(route, stop);
        BusTrip newBusTrip = new BusTrip(startLocation, tapInTime);
        if (this.hasTrip()) {
            Trip lastTrip = this.getLastTrip();
            if (this.isCheckedIn()) {
                lastTrip.removeLastTransitTrip();
                if (!lastTrip.hasTransitTrip()) {
                    this.removeLastTrip();
                }
            }
            if (this.hasTrip()) {
                lastTrip = this.getLastTrip();
                TransitTrip lastTransitTrip = lastTrip.getLastTransitTrip();
                if (lastTrip.hasEnded(tapInTime) || lastTransitTrip.isDisjointed(startLocation)) {
                    lastTrip.setEndTime(lastTrip.getTrips().get(lastTrip.getTrips().size() - 1).getEndTime());
                    this.addNewTripForBusTrip(newBusTrip);
                } else {
                    this.payForBusTrip(lastTrip);
                    lastTrip.addTransitTrip(newBusTrip);
                }
            } else {
                this.addNewTripForBusTrip(newBusTrip);
            }
        } else {
            this.addNewTripForBusTrip(newBusTrip);
        }
        this.checkIn();
    }

    /**
     * Using the card to perform the action of tapping out of a subway station at subway station station on subway line
     * line with time as tapOutTime
     *
     * @param station The name of the subway station
     * @param tapOutTime The time when the tapping action is performed
     * @param line The subway line where the subway station belongs
     */
    public void tapOutSubway(String station, long tapOutTime, String line) {
        if (this.hasTrip()) {
            SubwayStation endStation = SubwaySystem.getStationByName(line, station);
            Trip currentTrip = this.getLastTrip();
            TransitTrip currentTransitTrip = currentTrip.getLastTransitTrip();
            if (this.isCheckedIn() && currentTransitTrip.isSameTransitTrip(endStation)) {
                SubwayTrip currentSubwayTrip = (SubwayTrip) currentTransitTrip;
                if (!currentSubwayTrip.getStartLocation().getNodeName().equals(station)) {
                    currentSubwayTrip.setEndTime(tapOutTime);
                    currentTrip.setEndTime(tapOutTime);
                    currentSubwayTrip.setEndLocation(endStation);
                    currentSubwayTrip.setJourney(line, this);
                    this.payForSubwayTrip(currentTrip, currentSubwayTrip);
                } else {
                    currentTrip.removeLastTransitTrip();
                    if (!currentTrip.hasTransitTrip()) {
                        this.removeLastTrip();
                    }
                }
            } else if (this.isCheckedIn() && !currentTransitTrip.isSameTransitTrip(endStation)) {
                currentTrip.removeLastTransitTrip();
                if (!currentTrip.hasTransitTrip()) {
                    this.removeLastTrip();
                }
            }
        }
        this.checkOut();
    }

    /**
     * Using the card to perform the action of tapping out of a bus at bus stop stop on bus route route with time as
     * tapOutTime
     *
     * @param stop The stop number of the stop where the tapping action is performed
     * @param tapOutTime The time when the tapping action is performed
     * @param route The bus route where the bus stop belongs
     */
    public void tapOutBus(int stop, long tapOutTime, String route) {
        if (this.hasTrip()) {
            BusStop endStop = BusSystem.getStopByNumber(route, stop);
            Trip currentTrip = this.getLastTrip();
            TransitTrip currentTransitTrip = currentTrip.getLastTransitTrip();
            if (this.isCheckedIn() && currentTransitTrip.isSameTransitTrip(endStop)) {
                BusTrip currentBusTrip = (BusTrip) currentTransitTrip;
                currentBusTrip.setEndTime(tapOutTime);
                currentTrip.setEndTime(tapOutTime);
                currentBusTrip.setEndLocation(endStop);
                currentBusTrip.setJourney(route, this);
            } else if (this.isCheckedIn() && !currentTransitTrip.isSameTransitTrip(endStop)) {
                currentTrip.removeLastTransitTrip();
                if (!currentTrip.hasTransitTrip()) {
                    this.removeLastTrip();
                }
            }
        }
        this.checkOut();
    }

    /**
     * Return whether the list of trips is empty.
     *
     * @return True if the card had completed a trip; false otherwise.
     */
    private boolean hasTrip() {
        return this.listOfTrips.size() > 0;
    }

    /**
     * Return the last trip travelled by this card.
     *
     * @return Trip
     */
    private Trip getLastTrip() {
        return this.listOfTrips.get(this.listOfTrips.size() - 1);
    }

    /**
     * Set the transit card as checked in.
     */
    private void checkIn() {
        this.checkedIn = true;
    }

    /**
     * Set the transit card as checked out.
     */
    private void checkOut() {
        this.checkedIn = false;
    }

    /**
     * Return whether the transit card is checked in.
     *
     * @return boolean
     */
    private boolean isCheckedIn() {
        return this.checkedIn;
    }

    /**
     * Add a new trip for the subway trip newSubwayTrip.
     *
     * @param newSubwayTrip The subway trip to be added to the new trip
     */
    private void addNewTripForSubwayTrip(SubwayTrip newSubwayTrip) {
        Trip newTrip = new Trip(newSubwayTrip.getStartTime());
        newTrip.addTransitTrip(newSubwayTrip);
        this.listOfTrips.add(newTrip);
    }

    /**
     * Add a new trip for the bus trip newBusTrip.
     *
     * @param newBusTrip The bus trip to be added to the new trip
     */
    private void addNewTripForBusTrip(BusTrip newBusTrip) {
        Trip newTrip = new Trip(newBusTrip.getStartTime());
        this.payForBusTrip(newTrip);
        newTrip.addTransitTrip(newBusTrip);
        this.listOfTrips.add(newTrip);
    }

    /**
     * Activate the card.
     */
    public void activate() {
        this.active = true;
    }

    /**
     * Deactivate the card.
     */
    public void deactivate() {
        this.active = false;
    }

    /**
     * Remove the last trip travelled by the card.
     */
    private void removeLastTrip() {
        if (this.listOfTrips.size() > 0) {
            this.listOfTrips.remove(this.listOfTrips.size() - 1);
        }
    }

    /**
     * Set the transit card's balance as balance.
     *
     * @param balance Amount to be set as the card's balance.
     */
    void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Return the card number of the transit card.
     *
     * @return int
     */
    public int getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Return a list of trips travelled by the transit card.
     *
     * @return ArrayList<Controllers.Trip>
     */
    ArrayList<Trip> getListOfTrips() {
        return this.listOfTrips;
    }

    /**
     * Return the total amount of credit added to the transit card.
     *
     * @return double
     */
    double getAddedAmount() {
        return this.addedAmount;
    }

    /**
     * Return the amount of balance in the transit card.
     *
     * @return double
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Return whether the transit card is active or inactive.
     *
     * @return String "Active" if the card is active; "Inactive" if the card is not active.
     */
    public String getStatus() {
        String result = "Active";
        if (!this.active) result = "Inactive";
        return result;
    }

    /**
     * Return the total number of the subway stations reached by this transit card.
     *
     * @return int
     */
    public int getStationsReached() {
        return this.stationsReached;
    }

    /**
     * Increment the number of subway station reached by this transit card by one.
     */
    void increaseStationReached() {
        this.stationsReached++;
    }

    /**
     * Return the total number of the bus stops reached by this transit card.
     *
     * @return int
     */
    public int getStopsReached() {
        return this.stopsReached;
    }

    /**
     * Increment the number of bus stop reached by this transit card by one.
     */
    void increaseStopReached() {
        this.stopsReached++;
    }

    /**
     * Pay the appropriate amount for subway trip currentSubwayTrip.
     *
     * @param currentTrip       The trip which the subway trip belongs to.
     * @param currentSubwayTrip The subway trip which the transit card has travelled.
     */
    private void payForSubwayTrip(Trip currentTrip, SubwayTrip currentSubwayTrip) {
        int i = 0;
        while (currentTrip.getCurrentCost() < 6 && i < currentSubwayTrip.getJourney().size() - 1) {
            currentTrip.addCost(0.5);
            this.balance -= 0.5;
            i++;
        }
    }

    /**
     * Pay the appropriate amount for bus trip currentBusTrip.
     *
     * @param currentTrip The trip which the bus trip belongs to.
     */
    private void payForBusTrip(Trip currentTrip) {
        if (currentTrip.getCurrentCost() <= 4) {
            currentTrip.addCost(2);
            this.balance -= 2;
        } else if (currentTrip.getCurrentCost() < 6) {
            currentTrip.addCost(6 - currentTrip.getCurrentCost());
            this.balance -= 6 - currentTrip.getCurrentCost();
        }
    }
}