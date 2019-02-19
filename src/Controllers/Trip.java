package Controllers;

import Model.BusStop;

import java.util.ArrayList;
import java.util.Date;

// A "Controllers.Trip" can contain several Controllers.SubwayTrip and Controllers.BusTrip as long
// as all entrances are within 2 hours of the initial
// entrance that begin the trip
class Trip {
    private long startTime;
    private long endTime;
    private double currentCost;
    private ArrayList<TransitTrip> trips;

    /**
     * Constructor of a complete trip with information about the start time, end time, cost, and a
     * list of all trip segments tha combined the complete trip.
     *
     * @param startTime the time of starting the trip
     */
    Trip(long startTime) {
        this.startTime = startTime;
        this.currentCost = 0;
        this.trips = new ArrayList<>();
    }

    /**
     * Adds cost to the total amount of the trip.
     *
     * @param cost the added cost of the trip
     */
    void addCost(double cost) {
        this.currentCost += cost;
    }

    /**
     * Determine the end time of the trip.
     *
     * @param endTime the end time of the trip
     */
    void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Adds a transit trip segment to the trip.
     *
     * @param newTransitTrip the new transit trip to be added
     */
    void addTransitTrip(TransitTrip newTransitTrip) {
        this.trips.add(newTransitTrip);
    }

    /**
     * Removes the last recent transit trip from the list of all trip segments.
     */
    void removeLastTransitTrip() {
        if (this.hasTransitTrip()) {
            this.trips.remove(this.trips.size() - 1);
        }
    }

    /**
     * Returns whether the list of trips is empty.
     *
     * @return boolean
     */
    boolean hasTransitTrip() {
        return this.trips.size() > 0;
    }

    /**
     * Returns whether the trip is ended.
     *
     * @param time current time
     * @return boolean
     */
    boolean hasEnded(long time) {
        return time - this.getStartTime() > 7200000;
    }

    /**
     * Returns the last trip in the list of all trips.
     *
     * @return TransitTrip
     */
    TransitTrip getLastTransitTrip() {
        if (this.hasTransitTrip()) {
            return this.trips.get(this.trips.size() - 1);
        }
        return null;
    }

    /**
     * Returns the first trip in the list of all trips.
     *
     * @return TransitTrip
     */
    private TransitTrip getFirstTransitTrip() {
        if (this.hasTransitTrip()) {
            return this.trips.get(0);
        }
        return null;
    }

    /**
     * Returns the amount of the cost of the trip.
     *
     * @return double
     */
    double getCurrentCost() {
        return this.currentCost;
    }

    /**
     * Returns the start time of the trip.
     *
     * @return long
     */
    private long getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the trip.
     *
     * @return long
     */
    long getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the list of all transit trip segments in the trip.
     *
     * @return ArrayList<TransitTrip>
     */
    ArrayList<TransitTrip> getTrips() {
        return this.trips;
    }

    /**
     * Returns the complete trip, which is combined by all trip segments.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Date date = new Date(this.getStartTime());
        String start;
        if (this.getFirstTransitTrip() instanceof SubwayTrip) {
            start = "Station: " + this.getFirstTransitTrip().getStartLocation().getNodeName();
        } else {
            BusStop busStop = (BusStop) this.getFirstTransitTrip().getStartLocation();
            start = "Stop: " + busStop.getStopNumber();
        }
        String end;
        if (this.getLastTransitTrip().getEndLocation() == null) {
            end = "";
        } else {
            if (this.getLastTransitTrip() instanceof SubwayTrip) {
                end = "Station: " + this.getLastTransitTrip().getEndLocation().getNodeName();
            } else {
                BusStop busStop = (BusStop) this.getLastTransitTrip().getEndLocation();
                end = "Stop: " + busStop.getStopNumber();
            }
        }
        output
                .append(date.toString())
                .append(", ")
                .append(start)
                .append(", ")
                .append(end)
                .append(", ")
                .append(this.getCurrentCost());
        return output.toString();
    }
}
