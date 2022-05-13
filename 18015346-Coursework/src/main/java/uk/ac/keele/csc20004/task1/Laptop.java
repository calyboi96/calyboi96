/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.task1;

import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.HardwarePart;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.AbstractComputer;


/**
 * Concrete implementation of a Laptop 
 */

public class Laptop extends AbstractComputer {
    public Laptop(MotherBoard mb, GPU gpu, RAM ram1) {
        super(new HardwarePart[]{mb, gpu, ram1});
    }

    @Override
    public String toString() {
        return "Laptop" + super.toString();
    }

}
