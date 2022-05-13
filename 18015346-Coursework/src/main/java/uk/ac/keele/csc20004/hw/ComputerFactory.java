/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.hw;

import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;

/**
 * Interface defining the functionalities that are expected
 * in our simulation of a factory for the production of computers.
 * 
 */
public interface ComputerFactory {
    /**
     * Stores a RAM unit in the appropriate shelf
     * 
     * @param ram the component to be stored
     */
    public void storeRAM(RAM ram);

    /**
     * Retrieves a RAM component fron the shelf. Which component
     * is retrieved (the first, or last added) is not specified.
     * 
     * @return a unit of RAM
     */
    public RAM retrieveRAM();

    /**
     * Returns the nuber of RAM unit currently stored in the 
     * corresponding shelf.
     * 
     * @return the number of stored RAM
     */
    public int RAMShelfSize();

    /**
     * Stores a Motherboard units in the appropriate shelf
     * 
     * @param mb the component to be stored
     */
    public void storeMotherBoard(MotherBoard mb);

    /**
     * Retrieves a Motherboard component fron the shelf. Which component
     * is retrieved (the first, or last added) is not specified.
     * 
     * @return a motherboard component
     */
    public MotherBoard retrieveMotherBoard();

    /**
     * Returns the nuber of motherboard units currently stored in the 
     * corresponding shelf.
     * 
     * @return the number of stored motherboards
     */
    public int MBShelfSize();

    /**
     * Stores a GPU unit in the appropriate shelf
     * 
     * @param gpu the component to be stored
     */
    public void storeGPU(GPU gpu);

    /**
     * Retrieves a GPU component fron the shelf. Which component
     * is retrieved (the first, or last added) is not specified.
     * 
     * @return a motherboard component
     */
    public GPU retrieveGPU();

    /**
     * Returns the nuber of GPU units currently stored in the 
     * corresponding shelf.
     * 
     * @return the number of stored GPUs
     */
    public int GPUShelfSize();

    /**
     * Adds an assembled computer to the queue of packaged machines, 
     * ready to be sold
     * 
     * @param c the newly assembled Computer
     */
    public void enqueuePackagedComputer(Computer c);

    /**
     * Simulates the sale of a Computer, by retrieving an element 
     * from the queue of packaged machines.
     * 
     * @return a Computer extracted from the packaged queue
     */
    public Computer sell();

    /**
     * Returns the number of curretly packaged machines.
     * 
     * @return size of the queue holding the elements ready to be sold 
     */
    public int getNumPackages();
}
