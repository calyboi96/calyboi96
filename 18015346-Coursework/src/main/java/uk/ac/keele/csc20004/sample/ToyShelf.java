/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.sample;

import uk.ac.keele.csc20004.hw.Parameters;
import uk.ac.keele.csc20004.hw.parts.HardwarePart;
import uk.ac.keele.csc20004.hw.parts.HardwareShelf;

/**
 * A very basic implementation of a shelf to be used within the toy
 * ComputerFactory.
 * This code is only provided as a reference to show an example of use of 
 * the provided interfaces and methods. You are free to re-use parts of it, 
 * however note that it *does not* necessarily meet the requirements for either 
 * of the tasks 
 */
public class ToyShelf implements HardwareShelf {
    private HardwarePart[] storage;

    private int index = -1;

    /**
     * The constructor initialises the shelf with
     * the et maximum size.
     */
    public ToyShelf() {
        storage = new HardwarePart[Parameters.MAX_STORAGE_SIZE];
    }

    /**
     * Adds a new component to the storage area (no order is specified)
     * 
     * @param part the new hardware component to be added
     */
    public void storePart(HardwarePart part) {
        if (index < storage.length-1) {
            storage[++index] = part;
        }
    }

    /**
     * Retrieves a component from the storage area (no order is specified)
     * 
     * @return the hardware component extracted frm storage
     */
    public HardwarePart retrievePart() {
        HardwarePart part = null;
        if (index >= 0) {
            part = storage[index];
            storage[index] = null;
            index--;
        }

        return part;
    }

    @Override
    public int size() {
        return (index+1);
    }
}
