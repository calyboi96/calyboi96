package uk.ac.keele.csc20004.task2;


import java.util.Random;

import uk.ac.keele.csc20004.hw.Parameters;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.HardwarePartFactory;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.task1.FactoryShelf;

public class ShelfManager{
    static FactoryShelf mbstorage;
    static FactoryShelf gpustorage;
    static FactoryShelf ramstorage;
    static HardwarePartFactory f;

    public ShelfManager(FactoryShelf mbStorage, FactoryShelf gpuStorage, FactoryShelf ramStorage, HardwarePartFactory hardwarepartfactory) {
        mbstorage = mbStorage;
        gpustorage = gpuStorage;
        ramstorage = ramStorage;
        f = hardwarepartfactory;
    }

    
    public static class RamShelfThread extends Thread{

        public void run(){
            Random r = new Random();
            RAM ram = new RAM(0);
            while(true){
                synchronized(ramstorage){
                    int rndPartsRAM = r.nextInt(Parameters.MAX_STORAGE_SIZE);
                    try{
                        for (int i = 0; i < rndPartsRAM; i++) {
                            // note that we need to cast to (RAM)
                            // as f.createHardwarePart() returns a HardwarePart
                            ram = (RAM) f.createHardwarePart("RAM");
                        
                            ramstorage.storePart(ram);
                            System.out.println("Stored " + ram);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }
    }


    public static class GpuShelfThread extends Thread{

        public void run(){
            Random r = new Random();
            GPU gpu = new GPU(0);
            while(true){
                synchronized(gpustorage){
                    int rndPartsGPU = r.nextInt(Parameters.MAX_STORAGE_SIZE);
                    try{
                        for (int i = 0; i < rndPartsGPU; i++) {
                            // note that we need to cast to (GPU)
                            // as f.createHardwarePart() returns a HardwarePart
                            gpu = (GPU) f.createHardwarePart("GPU");
                        
                            gpustorage.storePart(gpu);
                            System.out.println("Stored " + gpu);
                        }
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                }
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MbShelfThread extends Thread{

        public void run(){
            Random r = new Random();
            MotherBoard mb = new MotherBoard(0);
            while(true){
                synchronized(mbstorage){
                    int rndPartsMB = r.nextInt(Parameters.MAX_STORAGE_SIZE);
                    try{
                        for (int i = 0; i < rndPartsMB; i++) {
                            // note that we need to cast to (MOTHERBOARD)
                            // as f.createHardwarePart() returns a HardwarePart
                            mb = (MotherBoard) f.createHardwarePart("MOTHERBOARD");
                        
                            mbstorage.storePart(mb);
                            System.out.println("Stored " + mb);
                        }
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                }
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void begin(){
        RamShelfThread ramshelf = new RamShelfThread();
        ramshelf.start();
        ramshelf.setName("RAMShelf");
        GpuShelfThread gpushelf = new GpuShelfThread();
        gpushelf.start();
        gpushelf.setName("GPUShelf");
        MbShelfThread mbshelf = new MbShelfThread();
        mbshelf.start();
        mbshelf.setName("MBShelf");
    }
}
