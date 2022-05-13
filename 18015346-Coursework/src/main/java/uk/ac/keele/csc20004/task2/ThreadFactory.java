package uk.ac.keele.csc20004.task2;

import java.util.ArrayList;
import java.util.Comparator;

import uk.ac.keele.csc20004.hw.ComputerFactory;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;
import uk.ac.keele.csc20004.task1.FactoryShelf;

/**
 * A very basic implementation of a ComputerFactory.
 * This code is only provided as a reference to show an example of use of 
 * the provided interfaces and methods. You are free to re-use parts of it, however note 
 * that it *does not* necessarily meet the requirements for either of the tasks (for instamce:
 * the package queue does not implement any priority mechanism)
 */
public class ThreadFactory implements ComputerFactory {
    private final FactoryShelf mbStorage;
    private final FactoryShelf gpuStorage;
    private final FactoryShelf ramStorage;

    private final ArrayList<Computer> packagequeue;

    public ThreadFactory(FactoryShelf mb, FactoryShelf gpu, FactoryShelf ram, ArrayList<Computer> packageQueue) {
        mbStorage = mb;
        gpuStorage = gpu;
        ramStorage = ram;

        packagequeue = packageQueue;
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
        packagequeue.add(c);
        System.out.println("Packaged: " + c);
        
    }

    @Override
    public Computer sell() {
        Computer c = packagequeue.stream().max(Comparator.comparing(Computer::getCost)).get();
        packagequeue.remove(packagequeue.indexOf(c));
        return c;
    }

    @Override
    public int getNumPackages() {
        return packagequeue.size();
    }
    
}
