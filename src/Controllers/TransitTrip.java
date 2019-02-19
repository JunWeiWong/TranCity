package Controllers;

import java.util.ArrayList;

import Model.*;

abstract class TransitTrip {
    long startTime;
    private long endTime;
    ArrayList<TransitNode> journey;

    /**
     * Returns whether the transit node is disjointed with another transit node.
     *
     * @param transitNode the transit node that need to be checked.
     * @return boolean
     */
    abstract boolean isDisjointed(TransitNode transitNode);

    /**
     * Returns whether the transit node is the same type of transit node, so that we can know is this
     * trip continuous.
     *
     * @param transitNode a transit node
     * @return boolean
     */
    abstract boolean isSameTransitTrip(TransitNode transitNode);

    /**
     * Return the end location of this transit trip.
     *
     * @return TransitNode
     */
    abstract TransitNode getEndLocation();

    /**
     * Return the start location of this transit trip.
     *
     * @return TransitTrip
     */
    abstract TransitNode getStartLocation();

    /**
     * adds a transit node into the journey.
     *
     * @param transitNode a added transit node
     */
    void addTransitNode(TransitNode transitNode) {
        this.journey.add(transitNode);
    }

    /**
     * sets the last transit node in the trip.
     *
     * @param transitNode a transit node
     */
    abstract void setEndLocation(TransitNode transitNode);

    /**
     * Abstracts a trip from a transit card to calculate the fare.
     *
     * @param path        a transit path
     * @param transitCard a transit card
     */
    abstract void setJourney(String path, TransitCard transitCard);

    /**
     * Return a list of all transit node reached during the transit trip.
     *
     * @return ArrayList<TransitNode>
     */
    ArrayList<TransitNode> getJourney() {
        return this.journey;
    }

    /**
     * Return the time when this transit trip starts.
     *
     * @return long
     */
    long getStartTime() {
        return this.startTime;
    }

    /**
     * Return the time when this transit trip ends.
     *
     * @return long
     */
    long getEndTime() {
        return this.endTime;
    }

    /**
     * sets the time when this transit trip ends.
     *
     * @param endTime the end time
     */
    void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
