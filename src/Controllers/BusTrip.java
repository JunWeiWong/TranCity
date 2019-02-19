package Controllers;

import Model.*;

import java.util.ArrayList;

class BusTrip extends TransitTrip {
    private BusStop startLocation;
    private BusStop endLocation;

    /**
     * Constructor of a bus trip, which includes the bus stop and the time of getting in a bus, the
     * bus stop of getting off a bus, and a list of all bus stops reached during the trip.
     *
     * @param startLocation the bus stop of getting in a bus
     * @param startTime     the time of getting in a bus
     */
    BusTrip(BusStop startLocation, long startTime) {
        this.startTime = startTime;
        this.startLocation = startLocation;
        this.journey = new ArrayList<>();
    }

    /**
     * Returns the bus trip with the stop number of the start bus stop, the total number of stops
     * reached, and the stop number of the end bus stop.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "BusTrip {StartLocation: "
                + this.startLocation.getStopNumber()
                + ", EndLocation: "
                + this.endLocation.getStopNumber()
                + "}";
    }

    /**
     * Returns whether the bus stop is disjointed with another transit node.
     *
     * @param transitNode the transit node that need to be checked.
     * @return boolean
     */
    @Override
    boolean isDisjointed(TransitNode transitNode) {
        if (transitNode instanceof BusStop) {
            return this.endLocation.getStopNumber() != ((BusStop) transitNode).getStopNumber();
        } else {
            return !this.endLocation.getIntersectStation().equals(transitNode.getNodeName());
        }
    }

    /**
     * Returns whether the transit node is a bus stop, so that we can know is this trip continuous.
     *
     * @param transitNode the transit node that need to be checked.
     * @return boolean
     */
    @Override
    boolean isSameTransitTrip(TransitNode transitNode) {
        if (transitNode instanceof BusStop) {
            return this.startLocation.getRoute().equals(((BusStop) transitNode).getRoute());
        }
        return false;
    }

    /**
     * Returns the last bus stop of the trip.
     *
     * @return BusStop
     */
    @Override
    BusStop getEndLocation() {
        return this.endLocation;
    }

    /**
     * Returns the first bus stop of the trip.
     *
     * @return BusStop
     */
    @Override
    BusStop getStartLocation() {
        return this.startLocation;
    }

    /**
     * Sets a bus top as the last stop of the trip.
     *
     * @param transitNode a bus stop
     */
    @Override
    void setEndLocation(TransitNode transitNode) {
        this.endLocation = (BusStop) transitNode;
    }

    /**
     * Abstracts a bus trip from a transit card to calculate the fare.
     *
     * @param route       a bus route
     * @param transitCard a transit card
     */
    @Override
    void setJourney(String route, TransitCard transitCard) {
        BusRoute busRoute = BusSystem.getBusRouteByName(route);
        int i = busRoute.getStopsList().indexOf(this.startLocation);
        while (busRoute.getStopsList().get(i).getStopNumber() != this.endLocation.getStopNumber()) {
            this.addTransitNode(busRoute.getStopsList().get(i));
            transitCard.increaseStopReached();
            i++;
        }
        this.addTransitNode(busRoute.getStopsList().get(i));
        double d =
                Distance.getDistance(
                        startLocation.getNodeLocation().get(0),
                        startLocation.getNodeLocation().get(1),
                        endLocation.getNodeLocation().get(0),
                        endLocation.getNodeLocation().get(1));
        Administrator.getListOfDays()
                .get(Administrator.getListOfDays().size() - 1)
                .incrementDistance(d);
        Administrator.getListOfDays()
                .get(Administrator.getListOfDays().size() - 1)
                .incrementPassengers();
    }
}
