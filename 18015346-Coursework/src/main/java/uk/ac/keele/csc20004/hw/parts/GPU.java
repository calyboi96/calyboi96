/* **********************
 * CSC-20004 COURSEWORK *
 * 2021/22 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.parts;

import uk.ac.keele.csc20004.hw.Parameters;

/**
 * A concrete implementation of a hardware coponent: a GPU
 * 
 */
public class GPU extends HardwarePart {
    public GPU(int serial) {
        super(serial);
    }

    @Override
    public float getCost() {
        return Parameters.GPU_COST;
    }
    
    @Override
    public String toString() {
        return "g-" + super.toString();
    }
}
