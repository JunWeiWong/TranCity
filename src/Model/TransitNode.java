package Model;

import java.util.ArrayList;

/**
 * This class is considered as the parent class for all types of transit stop-point, and this class
 * can make the system more expandable.
 */
public class TransitNode {
    String nodeName;
    ArrayList<Double> nodeLocation;

    /**
     * empty constructor.
     */
    TransitNode() {
    }

    /**
     * Returns the transit node name.
     *
     * @return String
     */
    public String getNodeName() {
        return this.nodeName;
    }

    /**
     * Returns a list of all transit nodes.
     *
     * @return ArrayList<Double>
     */
    public ArrayList<Double> getNodeLocation() {
        return nodeLocation;
    }

    public String toString() {
        return "Node " + this.nodeName + " at " + this.nodeLocation;
    }
}
