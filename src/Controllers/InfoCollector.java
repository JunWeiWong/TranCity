package Controllers;

public class InfoCollector {
    private String date;
    private double fareAccumulator;
    private int reached;
    private double distance;
    private int passengers;

    /**
     * Constructor of the fare system to calculate the fare, and record the date.
     *
     * @param date the date of a fare being paid
     */
    public InfoCollector(String date) {
        this.date = date;
    }

    /**
     * Adds the amount of fare to the fare accumulator.
     *
     * @param fare the amount of fare being added
     */
    public void incrementFare(double fare) {
        this.fareAccumulator += fare;
    }

    /**
     * Adds number of stops and station reached by a cardholder.
     *
     * @param num the number of stops or stations reached by cardholder
     */
    public void incrementReached(int num) {
        this.reached += num;
    }

    /**
     * Adds to the 1 to number of passenger per day
     */
    void incrementPassengers() {
        this.passengers += 1;
    }

    /**
     * Adds to the distance travelled per day
     *
     * @param d the distance travelled per passenger
     */
    void incrementDistance(double d) {
        this.distance += d;
    }

    /**
     * Returns the date of a fare being paid.
     *
     * @return String
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the total amount of the fare.
     *
     * @return double
     */
    double getFareAccumulator() {
        return this.fareAccumulator;
    }

    /**
     * Returns the total number of stops or station per day.
     *
     * @return int
     */
    int getReached() {
        return this.reached;
    }

    /**
     * Returns the total number of passengers per day
     *
     * @return int
     */
    int getPassengers() {
        return this.passengers;
    }

    /**
     * Returns the total of distance travelled per day.
     *
     * @return double
     */
    double getDistance() {
        return Math.round(this.distance);
    }

    /**
     * Returns the amount of fare collected in a certain date.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.date + ", " + this.fareAccumulator;
    }
}
