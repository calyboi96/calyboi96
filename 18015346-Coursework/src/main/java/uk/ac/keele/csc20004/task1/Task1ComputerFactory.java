/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.task1;

import java.util.ArrayList;
import java.util.Comparator;

import uk.ac.keele.csc20004.hw.ComputerFactory;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;

/**
 * Computer Factory Implementation
 */
public class Task1ComputerFactory implements ComputerFactory {
    private final FactoryShelf mbStorage;
    private final FactoryShelf gpuStorage;
    private final FactoryShelf ramStorage;

    private final ArrayList<Computer> packageQueue;

    public Task1ComputerFactory() {
        mbStorage = new FactoryShelf();
        gpuStorage = new FactoryShelf();
        ramStorage = new FactoryShelf();

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
//Organises computers then sells them in order of most expensive to least expensive 
    @Override
    public Computer sell() {

     Computer c = packageQueue.stream().max(Comparator.comparing(Computer::getCost)).get();
     packageQueue.remove(packageQueue.indexOf(c));
     return c;
    }

    @Override
    public int getNumPackages() {
        return packageQueue.size();
    }
    
}
