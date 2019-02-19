package Model;

import java.util.ArrayList;

public class SubwaySystem extends TransitSystem {
    public static ArrayList<SubwayLine> lines;

    /**
     * Constructor of the Model.SubwaySystem to collect all exist subway lines.
     */
    SubwaySystem() {
        lines = new ArrayList<>();
    }

    /**
     * Returns the line
     *
     * @param name the name of the line to look for
     * @return Model.SubwayLine
     */
    public static SubwayLine getSubwayLineByName(String name) {
        SubwayLine result = null;
        for (SubwayLine line : lines) {
            if (line.getLineName().equals(name)) {
                result = line;
            }
        }
        return result;
    }

    /**
     * Returns the subway station by searching station name.
     *
     * @param line        the subway line which contains the subway station
     * @param stationName the subway station name
     * @return SubwayStation
     */
    public static SubwayStation getStationByName(String line, String stationName) {
        for (SubwayLine temp : lines) {
            if (temp.getLineName().equals(line)) {
                for (SubwayStation station : temp.getStationList()) {
                    if (station.getNodeName().equals(stationName)) {
                        return station;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns a list of all subway lines.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (SubwayLine line : lines) {
            result.append(line.getLineName());
            if (!lines.get(lines.size() - 1).getLineName().equals(line.getLineName())) {
                result.append(", ");
            }
        }
        result.append("}");
        return result.toString();
    }
}
