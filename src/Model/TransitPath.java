package Model;

/**
 * This class is considered as the parent class for all types of transit driving-path, and this
 * class can make the system more expandable.
 */
public class TransitPath {
    String pathName;
    int numberOfNodes;

    @Override
    public String toString() {
        return "Transit path " + ", " + this.pathName + ".";
    }
}
