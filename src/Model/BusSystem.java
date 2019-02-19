package Model;

import java.util.ArrayList;

public class BusSystem extends TransitSystem {
    public static ArrayList<BusRoute> routes;

    /**
     * Constructor of the Model.BusSystem to collect all exist bus routes.
     */
    BusSystem() {
        routes = new ArrayList<>();
    }

    /**
     * Returns the bus stop by searching stop number.
     *
     * @param route      the bus route which contains the bus stop
     * @param stopNumber the bus stop number
     * @return BusStop
     */
    public static BusStop getStopByNumber(String route, int stopNumber) {
        for (BusRoute temp : routes) {
            if (temp.getRouteName().equals(route)) {
                for (BusStop stop : temp.getStopsList()) {
                    if (stop.getStopNumber() == stopNumber) {
                        return stop;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the bus route by searching stop name.
     *
     * @param name the bus stop name
     * @return BusRoute
     */
    public static BusRoute getBusRouteByName(String name) {
        BusRoute result = null;
        for (BusRoute route : routes) {
            if (route.getRouteName().equals(name)) {
                result = route;
            }
        }
        return result;
    }

    /**
     * Returns a list of all bus routes.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (BusRoute route : routes) {
            result.append(route.getRouteName());
            if (!routes.get(routes.size() - 1).getRouteName().equals(route.getRouteName())) {
                result.append(", ");
            }
        }
        result.append("}");
        return result.toString();
    }
}
