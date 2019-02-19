package Model;

import java.util.ArrayList;

public class BusRoute extends TransitPath {
    private ArrayList<BusStop> stopsList;
    private BusStop startStop;
    private BusStop endStop;

    /**
     * Constructor of a bus route, which contains the route name, all stops along the route, the start
     * stop and the end stop, and the total number of stops along the route.
     *
     * @param name the name of the bus route.
     */
    BusRoute(String name) {
        this.pathName = name;
        this.stopsList = new ArrayList<>();
    }

    /**
     * Add a bus stop to the list of all stops.
     *
     * @param stop the added new stop
     */
    void addStop(BusStop stop) {
        this.stopsList.add(stop);
        this.numberOfNodes += 1;
    }

    /**
     * Determine the first(start) stop of the route.
     *
     * @param stop the stop is set to be the first(start) stop of the route
     */
    void setStartStop(BusStop stop) {
        this.startStop = stop;
    }

    /**
     * Determine the last(end) stop of the route.
     *
     * @param stop the stop is set to be the last(end) stop of the route
     */
    void setEndStop(BusStop stop) {
        this.endStop = stop;
    }

    /**
     * Returns the name of the route.
     *
     * @return String
     */
    public String getRouteName() {
        return this.pathName;
    }

    /**
     * Returns the first(start) stop of the route.
     *
     * @return Model.BusStop
     */
    BusStop getStartStop() {
        return this.startStop;
    }

    /**
     * Returns the last(end) stop of the route.
     *
     * @return Model.BusStop
     */
    BusStop getEndStop() {
        return this.endStop;
    }

    /**
     * Returns a list of all stops along the route.
     *
     * @return ArrayList<Model.BusStop>
     */
    public ArrayList<BusStop> getStopsList() {
        return this.stopsList;
    }

    /**
     * Returns the bus route with its route name.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Bus Route " + ", " + this.pathName + ".";
    }
}
