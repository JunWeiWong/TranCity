package Model;

import java.util.ArrayList;

public class SubwayStation extends TransitNode {
  private int intersectStop;
  private String line;

  /**
   * Constructor of a no-intersect subway station, which contains the station name, the coordinates
   * of the station, and the belonging subway line.
   *
   * @param name the name of the subway station
   * @param location the coordinates of the bus station
   * @param line the subway line that contains the subway station
   */
  SubwayStation(String name, ArrayList<Double> location, String line) {
    this.nodeName = name;
    this.nodeLocation = location;
    this.intersectStop = 0;
    this.line = line;
  }

  /**
   * Constructor of an intersect subway station, which contains the station name, the coordinates of
   * the station, the belonging subway line, and the stop number of the intersected bus stop.
   *
   * @param name the name of the subway station
   * @param location he coordinates of the bus station
   * @param intersection the stop number of the intersected bus stop
   * @param line the subway line that contains the subway station
   */
  SubwayStation(String name, ArrayList<Double> location, int intersection, String line) {
    this.nodeName = name;
    this.nodeLocation = location;
    this.intersectStop = intersection;
    this.line = line;
  }

  /**
   * Returns the stop number of the intersected bus stop.
   *
   * @return int
   */
  public int getIntersectStop() {
    return this.intersectStop;
  }

  /**
   * Returns the subway station with its name, coordinates, and the stop number of the intersected
   * bus stop if any.
   *
   * @return String
   */
  public String getLine() {
    return this.line;
  }

    @Override
  public String toString() {
    String output = "Station " + this.nodeName + " at " + this.nodeLocation;
    if (this.intersectStop != 0) {
      output += " intersects with Stop: " + this.intersectStop;
    }
    return output;
  }
}
