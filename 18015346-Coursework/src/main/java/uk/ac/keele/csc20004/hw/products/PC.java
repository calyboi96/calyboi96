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
 * A concrete implementation of a Computer: a PC. 
 * Bascially, the constructor just contraints the hardware components 
 * to be the ones that define a PC according to the coursework specs
 */
public class PC extends AbstractComputer {
    public PC(MotherBoard mb, GPU gpu, RAM ram1, RAM ram2) {
        super(new HardwarePart[]{mb, gpu, ram1, ram2});
    }
    
    @Override
    public String toString() {
        return "PC " + super.toString();
    }
}
