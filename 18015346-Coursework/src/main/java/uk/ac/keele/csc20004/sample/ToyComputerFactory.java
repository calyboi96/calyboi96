/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.sample;

import java.util.ArrayList;

import uk.ac.keele.csc20004.hw.ComputerFactory;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;

/**
 * A very basic implementation of a ComputerFactory.
 * This code is only provided as a reference to show an example of use of 
 * the provided interfaces and methods. You are free to re-use parts of it, however note 
 * that it *does not* necessarily meet the requirements for either of the tasks (for instamce:
 * the package queue does not implement any priority mechanism)
 */
public class ToyComputerFactory implements ComputerFactory {
    private final ToyShelf mbStorage;
    private final ToyShelf gpuStorage;
    private final ToyShelf ramStorage;

    private final ArrayList<Computer> packageQueue;

    public ToyComputerFactory() {
        mbStorage = new ToyShelf();
        gpuStorage = new ToyShelf();
        ramStorage = new ToyShelf();

        packageQueue = new ArrayList<>();
    }

    @Override
    public void storeRAM(RAM ram) {
        ramStorage.storePart(ram);
        System.out.println("Stored " + ram);
    }

    @Override
    public RAM retrieveRAM() {
        return (RAM)ramStorage.retrievePart();
    }

    @Override
    public int RAMShelfSize() {
        return ramStorage.size();
    }

    @Override
    public void storeMotherBoard(MotherBoard mb) {
        mbStorage.storePart(mb);
        System.out.println("Stored " + mb);
    }

    @Override
    public MotherBoard retrieveMotherBoard() {
        return (MotherBoard)mbStorage.retrievePart();
    }

    @Override
    public int MBShelfSize() {
        return mbStorage.size();
    }

    @Override
    public void storeGPU(GPU gpu) {
        gpuStorage.storePart(gpu);
        System.out.println("Stored " + gpu);
    }

    @Override
    public GPU retrieveGPU() {
        return (GPU)gpuStorage.retrievePart();
    }

    @Override
    public int GPUShelfSize() {
        return gpuStorage.size();
    }

    @Override
    public void enqueuePackagedComputer(Computer c) {
        packageQueue.add(c);
        System.out.println("Packaged: " + c);
        
    }

    @Override
    public Computer sell() {
        Computer c = packageQueue.remove(0);
        return c;
    }

    @Override
    public int getNumPackages() {
        return packageQueue.size();
    }
    
}
