/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.parts;

import uk.ac.keele.csc20004.hw.Parameters;

/**
 * A concrete implementation of a hardware coponent: a RAM unit
 * 
 */

public class RAM extends HardwarePart {
    public RAM(int serial) {
        super(serial);
    }

    @Override
    public float getCost() {
        return Parameters.RAM_COST;
    }
    
    @Override
    public String toString() {
        return "r-" + super.toString();
    }
}
