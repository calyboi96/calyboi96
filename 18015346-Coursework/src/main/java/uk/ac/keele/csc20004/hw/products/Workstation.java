/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.products;

import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.HardwarePart;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;

/**
 * A concrete implementation of a Computer: a Workstation. 
 * Bascially, the constructor just contraints the hardware components 
 * to be the ones that define a Workstation according to the coursework specs
 */

public class Workstation extends AbstractComputer {
    public Workstation(MotherBoard mb, GPU gpu, RAM ram1, RAM ram2, RAM ram3, RAM ram4) {
        super(new HardwarePart[]{mb, gpu, ram1, ram2, ram3, ram4});
    }

    @Override
    public String toString() {
        return "Workstation " + super.toString();
    }

}
