package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransitMap {
    private String transitName;
    private BusSystem bus;
    private SubwaySystem subway;

    /**
     * Constructor of the entire transit system, which combined both bus system and subway system.
     *
     * @param transitName the name of the entire transit system.
     */
    public TransitMap(String transitName) {
        this.transitName = transitName;
        // or we can generate the map inside of the constructor
        // Read the files to create the map of Subway lines and Routes lines

        String[] files = {"Line1", "Line2", "Route6Bay", "Route26Dupont", "Route30Lambton"};
        this.bus = new BusSystem();
        this.subway = new SubwaySystem();

        for (String file : files) {
            String line;

            try {
                FileReader fileReader = new FileReader(file + ".txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                if (file.startsWith("Line")) {
                    // We implement SubwayLine class
                    SubwayLine newLine = new SubwayLine(file);

                    while ((line = bufferedReader.readLine()) != null) {
                        Object[] parts = line.split(",");

                        if (parts.length == 3) {
                            // this means the station does not intersect, so we create the station with no
                            // intersection

                            ArrayList<Double> location = new ArrayList<>();
                            location.add(Double.valueOf((String) parts[0])); // for latitude
                            location.add(Double.valueOf((String) parts[1])); // for longitude
                            SubwayStation station = new SubwayStation((String) parts[2], location, file);
                            newLine.addStation(station);

                        } else {
                            // this means the station intersects, so we create the station with intersection

                            ArrayList<Double> location = new ArrayList<>();
                            location.add(Double.valueOf((String) parts[0])); // for latitude
                            location.add(Double.valueOf((String) parts[1])); // for longitude
                            SubwayStation station =
                                    new SubwayStation(
                                            (String) parts[2], location, Integer.valueOf((String) parts[3]), file);
                            newLine.addStation(station);
                        }
                    }

                    // We add the Line into the Subway system ttcSubway
                    SubwaySystem.lines.add(newLine);

                } else if (file.startsWith("Route")) {
                    // to implement Bus Route class
                    BusRoute newRoute = new BusRoute(file);

                    while ((line = bufferedReader.readLine()) != null) {
                        Object[] parts = line.split(",");

                        if (parts.length == 4) {
                            // this means the stop does not intersect so we create the stop with no intersection

                            ArrayList<Double> location = new ArrayList<>();
                            location.add(Double.valueOf((String) parts[0])); // for latitude
                            location.add(Double.valueOf((String) parts[1])); // for longitude
                            BusStop stop =
                                    new BusStop(
                                            Integer.valueOf((String) parts[2]), (String) parts[3], location, file);
                            newRoute.addStop(stop);

                        } else {
                            // this means the stop intersects so we create the stop with intersection

                            ArrayList<Double> location = new ArrayList<>();
                            location.add(Double.valueOf((String) parts[0])); // for latitude
                            location.add(Double.valueOf((String) parts[1])); // for longitude
                            BusStop stop =
                                    new BusStop(
                                            Integer.valueOf((String) parts[2]),
                                            (String) parts[3],
                                            location,
                                            (String) parts[4],
                                            file);
                            newRoute.addStop(stop);
                        }
                    }

                    // We set the Start Stop and End Stop
                    newRoute.setStartStop(newRoute.getStopsList().get(0));
                    newRoute.setEndStop(newRoute.getStopsList().get(newRoute.getStopsList().size() - 1));

                    // We add the Route into the Bus system
                    BusSystem.routes.add(newRoute);
                }
                // Closing bufferedReader
                bufferedReader.close();

            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + file + "'");
            } catch (IOException ex) {
                System.out.println("Error reading file '" + file + "'");
            }
        }
    }

    /**
     * Returns the bus system in the transit system.
     *
     * @return Model.BusSystem
     */
    BusSystem getBus() {
        return this.bus;
    }

    /**
     * Returns the subway system in the transit system.
     *
     * @return Model.SubwaySystem
     */
    SubwaySystem getSubway() {
        return this.subway;
    }
}
