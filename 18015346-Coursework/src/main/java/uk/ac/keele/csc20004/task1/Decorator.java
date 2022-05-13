/* **********************
 * CSC-20004 COURSEWORK 
 * STUDENT ID 18015346  *
 * **********************/
package uk.ac.keele.csc20004.task1;

import uk.ac.keele.csc20004.hw.products.Computer;

/**
Takes an existing computer, and applies Deluxe Packaging/wrapping to it
 * 
 */
public abstract class Decorator implements Computer {    
    protected Computer wrappedComputer;
    public  Decorator(Computer wrappedComputer){
        this.wrappedComputer=wrappedComputer;
    }
    /**
     * Returns the cost of a computer with Deluxe Packaging
     * @return*/
    public float getCost() {return wrappedComputer.getCost();}

    /**Retrieves the production time for a computer with Deluxe Packaging
     */
    public int getProductionTime() {return wrappedComputer.getProductionTime();}
}