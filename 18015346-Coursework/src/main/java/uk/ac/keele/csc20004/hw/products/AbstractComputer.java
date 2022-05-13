/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.products;

import java.util.Arrays;

import uk.ac.keele.csc20004.hw.parts.HardwarePart;

/**
 * This abstract class provides a basic implementation of the 
 * methods common to all types of machines. In particular, it 
 * defines the cost as the sum of the costs of the components, and 
 * the production time as equal to the cost.
 */
public abstract class AbstractComputer implements Computer {
    protected HardwarePart[] parts;

    /**
     * Any machine is defined as a collection of parts, so 
     * the constructor just takes an array of generic HardwarePart
     * 
     * @param parts an array containing the components
     */
    public AbstractComputer(HardwarePart[] parts) {
        this.parts = new HardwarePart[parts.length];
        this.parts = Arrays.copyOf(parts, this.parts.length);
    }
    
    @Override
    public float getCost() {
        float cost = 0;
        for (HardwarePart p : parts) {
            cost += p.getCost();
        }

        return cost;
    }

    @Override
    public int getProductionTime() {
        return Math.round(getCost());
    }

    @Override
    public String toString() {
        String descr = "[ ";

        for (HardwarePart p : parts) {
            descr += p.toString() + " ";
        }

        descr += "]";
        return descr;
    }
}
