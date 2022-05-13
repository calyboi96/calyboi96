/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.task1;

import java.util.Random;

import uk.ac.keele.csc20004.hw.ComputerFactory;
import uk.ac.keele.csc20004.hw.Parameters;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.HardwarePartFactory;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;
import uk.ac.keele.csc20004.hw.products.PC;
import uk.ac.keele.csc20004.hw.products.Workstation;
/**
Student ID: 18015346 CSC 20004 Coursework
 */
public class Task1Demo {
    public static void main(String[] args) throws InterruptedException {
        // create random object
        Random r = new Random();

        HardwarePartFactory f = HardwarePartFactory.getFactory();
        ComputerFactory shop = new Task1ComputerFactory();

        while (true) {
            // fill storage shelves with random number of parts
            try {
                int rndPartsRAM = r.nextInt(Parameters.MAX_STORAGE_SIZE);
                for (int i = 0; i < rndPartsRAM; i++) {
                    // note that we need to cast to (RAM)
                    // as f.createHardwarePart() returns a HardwarePart
                    shop.storeRAM((RAM) f.createHardwarePart("RAM"));
                }

                int rndPartsMB = r.nextInt(Parameters.MAX_STORAGE_SIZE);
                for (int i = 0; i < rndPartsMB; i++) {
                    shop.storeMotherBoard((MotherBoard) f.createHardwarePart("MOTHERBOARD"));
                }

                int rndPartsGPU = r.nextInt(Parameters.MAX_STORAGE_SIZE);
                for (int i = 0; i < rndPartsGPU; i++) {
                    shop.storeGPU((GPU) f.createHardwarePart("GPU"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // simulates production of a few Computers
            int numComputers = r.nextInt(10) + 1;

            String[] choices = { "PC", "WorkStation", "Laptop" };
            for (int i = 0; i < numComputers; i++) {
                int selection = r.nextInt(choices.length);

                // PC
                if (selection == 0) {
                    // if not enough components, break loop
                    if (shop.MBShelfSize() < 1 ||
                            shop.GPUShelfSize() < 1 ||
                            shop.RAMShelfSize() < 2) {
                        break;
                    }

                    MotherBoard mb = shop.retrieveMotherBoard();
                    GPU gpu = shop.retrieveGPU();
                    RAM ram1 = shop.retrieveRAM();
                    RAM ram2 = shop.retrieveRAM();

                    try {
                        if(r.nextBoolean()){
                            Computer pc = new PC(mb, gpu, ram1, ram2);
                            Computer deluxePC = new Deluxe(pc);
                            Thread.sleep(deluxePC.getProductionTime());
   
                            shop.enqueuePackagedComputer(deluxePC);
                        } else {
                            Computer pc = new PC(mb, gpu, ram1, ram2);
                            Thread.sleep(pc.getProductionTime());
   
                            shop.enqueuePackagedComputer(pc);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (selection == 1) { // Workstation
                    // if not enough components, break loop
                    if (shop.MBShelfSize() < 1 ||
                            shop.GPUShelfSize() < 1 ||
                            shop.RAMShelfSize() < 4) {
                        break;
                    }

                    MotherBoard mb = shop.retrieveMotherBoard();
                    GPU gpu = shop.retrieveGPU();
                    RAM ram1 = shop.retrieveRAM();
                    RAM ram2 = shop.retrieveRAM();
                    RAM ram3 = shop.retrieveRAM();
                    RAM ram4 = shop.retrieveRAM();

                    try {
                        if(r.nextBoolean()){
                            Computer ws = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);
                            Computer deluxeWS = new Deluxe(ws);
                            Thread.sleep(deluxeWS.getProductionTime());

                            shop.enqueuePackagedComputer(deluxeWS);
                        } else {
                            Computer ws = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);
                            Thread.sleep(ws.getProductionTime());

                            shop.enqueuePackagedComputer(ws);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (selection == 2) { // Laptop
                    // if not enough components, break loop
                    if (shop.MBShelfSize() < 1 ||
                            shop.GPUShelfSize() < 1 ||
                            shop.RAMShelfSize() < 1) {
                        break;
                    }

                    MotherBoard mb = shop.retrieveMotherBoard();
                    GPU gpu = shop.retrieveGPU();
                    RAM ram1 = shop.retrieveRAM();


                    try {
                        if(r.nextBoolean()){
                            Computer lp = new Laptop(mb, gpu, ram1);
                            Computer deluxeLP = new Deluxe(lp);
                            Thread.sleep(deluxeLP.getProductionTime());

                            shop.enqueuePackagedComputer(deluxeLP);
                        } else {
                            Computer lp = new Laptop(mb, gpu, ram1);
                            Thread.sleep(lp.getProductionTime());

                            shop.enqueuePackagedComputer(lp);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            // sell them
            while (shop.getNumPackages() > 0) {
                try {
                    Computer c = shop.sell();
                    Thread.sleep(r.nextInt(2000));

                    System.out.println("Sold: " + c + " - cost: " + c.getCost());
                    } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
