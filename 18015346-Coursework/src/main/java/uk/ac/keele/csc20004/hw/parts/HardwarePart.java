/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.parts;

/**
 * An abstract class defining the basic characteristics of a hardware
 * component for our (simulated) computer factory: a component will
 * be characterised by a unique serial number, a cost and the time it
 * takes to produce it (in this implementation, it is assumed that the
 * time, in ms, is the same as the cost).
 */
public abstract class HardwarePart {
    protected final int serial;

    /**
     * The constructor will only need the serial number; the 
     * cost is not known in this abstract implementation and
     * will need to be specified in the concrete ones.
     * 
     * @param serial the serial number for this part
     */
    public HardwarePart(int serial) {
        this.serial = serial;
    }

    /**
     * Return the serial number of the part
     * 
     * @return the serial number of the part
     */
    public int getSerial() {
        return serial;
    }

    /**
     * Returns the cost (in "money units") of this part
     * 
     * @return the of this part
     */
    public abstract float getCost();

    /**
     * This method can be used when simulating the production
     * of a hardware component by putting a thread to sleep.
     * 
     * @return the time (in ms) it takes to produce this component
     */
    public int getProdutionTime() {
        return Math.round(getCost());
    }

    @Override
    public String toString() {
        return Integer.toString(serial);
    }
}
