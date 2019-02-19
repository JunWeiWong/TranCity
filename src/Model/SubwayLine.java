package Model;

import java.util.ArrayList;

public class SubwayLine extends TransitPath {
    private ArrayList<SubwayStation> stationsList;

    /**
     * Constructor of a subway line, which contains the line name, all stations along the line, and
     * the total number of stations along the line.
     *
     * @param name the name of the subway station
     */
    SubwayLine(String name) {
        this.pathName = name;
        this.stationsList = new ArrayList<>();
    }

    /**
     * Adds a subway station to the list of all stations.
     *
     * @param newStation the added subway station
     */
    void addStation(SubwayStation newStation) {
        this.stationsList.add(newStation);
        this.numberOfNodes += 1;
    }

    /**
     * Returns a Subway Station
     *
     * @param name the name of the subway station to look for
     * @return Model.SubwayStation
     */
    SubwayStation getSubwayStationByName(String name) {
        SubwayStation result = null;
        for (SubwayStation station : this.stationsList) {
            if (station.getNodeName().equals(name)) {
                result = station;
            }
        }
        return result;
    }

    /**
     * Returns the name of the subway line.
     *
     * @return String
     */
    public String getLineName() {
        return this.pathName;
    }

    /**
     * Returns the list of all stations along the subway line.
     *
     * @return ArrayList<Model.SubwayStation>
     */
    public ArrayList<SubwayStation> getStationList() {
        return this.stationsList;
    }

    /**
     * Returns the subway line with its line name.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Subway Line " + ", " + this.pathName + ".";
    }
}
