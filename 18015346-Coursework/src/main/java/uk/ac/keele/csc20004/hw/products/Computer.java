/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw.products;

/**
 * Interface defining the  basic characteristics of a computer for 
 * our (simulated) factory. Just as for the hardware parts, an assembled
 * computer is characterised by having a cost and a production time.
 * 
 */
public interface Computer {    

    /**
     * Returns the cost (in "money units") of this machine.
     * The cost will typically result from the sum of the costs 
     * of its hardware components. 
     * 
     * @return the of this machine
     */
    public float getCost();

    /**
     * This method can be used when simulating the production
     * of a new machine by putting a thread to sleep.
     * 
     * @return the time (in ms) it takes to produce this component
     */
    public int getProductionTime();
}
