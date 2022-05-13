/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.parts;

/**
 * Interface defining the basic methods for the data structure
 * representing the storage areas for hardware parts. 
 * The choice of an actual data structure for the concrete mplementation 
 * is left as part of the coursewrok.
 */
public interface HardwareShelf {

    /**
     * Adds a new component to the storage area (no order is specified)
     * 
     * @param part the new hardware component to be added
     */
    public void storePart(HardwarePart part);

    /**
     * Retrieves a component from the storage area (no order is specified)
     * 
     * @return the hardware component extracted frm storage
     */
    public HardwarePart retrievePart();

    /**
     * Returns the nuber of units currently stored in the 
     * shelf.
     * 
     * @return the number of stored units
     */
    public int size();
}
