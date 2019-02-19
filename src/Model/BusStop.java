package Model;

import java.util.ArrayList;

public class BusStop extends TransitNode {
  private int stopNumber;
  private String intersectStation;
  private String route;

  /**
   * Constructor of a no-intersect bus stop, which contains the stop number, the stop name, the
   * coordinates of the stop, and the belonging bus route.
   *
   * @param number the stop number of the bus stop
   * @param name the name of the bus stop
   * @param location the coordinates of the bus stop
   * @param route the bus route that contains the bus stop
   */
  BusStop(int number, String name, ArrayList<Double> location, String route) {
    this.stopNumber = number;
    this.nodeName = name;
    this.nodeLocation = location;
    this.intersectStation = null;
    this.route = route;
  }

  /**
   * Constructor of an intersect bus stop, which contains the stop number, the stop name, the
   * coordinates of the stop, the belonging bus route, and the name of the intersected subway
   * station.
   *
   * @param number the stop number of the bus stop
   * @param name the name of the bus stop
   * @param location the coordinates of the bus stop
   * @param intersect the name of intersected subway station with the bus stop
   * @param route the bus route that contains the bus stop
   */
  BusStop(int number, String name, ArrayList<Double> location, String intersect, String route) {
    this.stopNumber = number;
    this.nodeName = name;
    this.nodeLocation = location;
    this.intersectStation = intersect;
    this.route = route;
  }

  /**
   * Returns the stop number of the stop.
   *
   * @return int
   */
  public int getStopNumber() {
    return this.stopNumber;
  }

  /**
   * Returns the name of intersected subway station.
   *
   * @return String
   */
  public String getIntersectStation() {
    return this.intersectStation;
  }

  /**
   * Returns the bus stop with its name, coordinates, and the name of intersected subway station if
   * any.
   *
   * @return String
   */
  public String getRoute() {
    return this.route;
  }

    @Override
  public String toString() {
    String output = "Station " + this.nodeName + " at " + this.nodeLocation;
    if (this.intersectStation != null) {
      output += " intersects with Station: " + this.intersectStation;
    }
    return output;
  }
}
