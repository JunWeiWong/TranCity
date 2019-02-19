package Controllers;

import Model.*;

import java.util.ArrayList;

import Controllers.Distance;

class SubwayTrip extends TransitTrip {
    private SubwayStation startLocation;
    private SubwayStation endLocation;

    /**
     * Constructor of a subway trip, which includes the subway station and the time of getting in a
     * subway, the subway station of getting off a subway, and a list of all subway stations reached
     * during the trip.
     *
     * @param startLocation the subway station of getting in a subway
     * @param startTime     the time of getting in a subway
     */
    SubwayTrip(SubwayStation startLocation, long startTime) {
        this.startTime = startTime;
        this.startLocation = startLocation;
        this.journey = new ArrayList<>();
    }

    /**
     * Returns the subway trip with the station name of the start subway station, the total number of
     * stations reached, and the station name of the end subway station.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "SubwayTrip {StartLocation: "
                + this.startLocation.getNodeName()
                + ", EndLocation: "
                + this.endLocation.getNodeName()
                + "}";
    }

    /**
     * Returns whether the subway station is disjointed with another transit node.
     *
     * @param transitNode the transit node that need to be checked.
     * @return boolean
     */
    @Override
    boolean isDisjointed(TransitNode transitNode) {
        if (transitNode instanceof SubwayStation) {
            return this.endLocation != transitNode;
        } else {
            return this.endLocation.getIntersectStop() != ((BusStop) transitNode).getStopNumber();
        }
    }

    /**
     * Returns whether the transit node is a subway station, so that we can know is this trip
     * continuous.
     *
     * @param transitNode the transit node that need to be checked.
     * @return boolean
     */
    @Override
    boolean isSameTransitTrip(TransitNode transitNode) {
        if (transitNode instanceof SubwayStation) {
            return this.startLocation.getLine().equals(((SubwayStation) transitNode).getLine());
        }
        return false;
    }

    /**
     * Sets a subway station as the last stop of the trip.
     *
     * @param transitNode a subway station
     */
    @Override
    void setEndLocation(TransitNode transitNode) {
        this.endLocation = (SubwayStation) transitNode;
    }

    /**
     * Returns the last subway station of the trip.
     *
     * @return SubwayStation
     */
    @Override
    SubwayStation getEndLocation() {
        return this.endLocation;
    }

    /**
     * Returns the first subway station of the trip.
     *
     * @return SubwayStation
     */
    @Override
    SubwayStation getStartLocation() {
        return this.startLocation;
    }

    /**
     * Abstracts a subway trip from a transit card to calculate the fare.
     *
     * @param line        a subway line
     * @param transitCard a transit card
     */
    @Override
    void setJourney(String line, TransitCard transitCard) {
        SubwayLine subwayLine = SubwaySystem.getSubwayLineByName(line);
        int startIndex = subwayLine.getStationList().indexOf(this.startLocation);
        int endIndex = subwayLine.getStationList().indexOf(this.endLocation);
        if (startIndex < endIndex) {
            while (!subwayLine
                    .getStationList()
                    .get(startIndex)
                    .getNodeName()
                    .equals(this.endLocation.getNodeName())) {
                this.addTransitNode(subwayLine.getStationList().get(startIndex));
                transitCard.increaseStationReached();
                startIndex++;
            }
            this.addTransitNode(subwayLine.getStationList().get(startIndex));
        } else {
            while (!subwayLine
                    .getStationList()
                    .get(startIndex)
                    .getNodeName()
                    .equals(this.endLocation.getNodeName())) {
                this.addTransitNode(subwayLine.getStationList().get(startIndex));
                transitCard.increaseStationReached();
                startIndex--;
            }
            this.addTransitNode(subwayLine.getStationList().get(startIndex));
        }
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
