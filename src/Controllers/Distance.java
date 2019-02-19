package Controllers;

class Distance {

    /**
     * Returns the direct distance between two coordinates.
     *
     * @param lat1 x coordinate of place1.
     * @param lon1 y coordinate of place1.
     * @param lat2 x coordinate of place2.
     * @param lon2 y coordinate of place2.
     * @return double
     */
    static double getDistance(Double lat1, Double lon1, Double lat2, Double lon2) {

        double R = 6373; // metres
        double φ1 = Math.toRadians(lat1);
        double φ2 = Math.toRadians(lat2);
        double Δφ = Math.toRadians(lat2 - lat1);
        double Δλ = Math.toRadians(lon2 - lon1);
        double a =
                Math.sin(Δφ / 2) * Math.sin(Δφ / 2)
                        + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double d = R * c;
        return Math.round(d * 100.0) / 100.0;
    }
}
