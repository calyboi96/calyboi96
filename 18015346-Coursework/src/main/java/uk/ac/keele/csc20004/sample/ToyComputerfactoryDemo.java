/* **********************
 * CSC-20004 COURSEWORK *
 * 2020/21 First sit    *
 * **********************/
package uk.ac.keele.csc20004.sample;

import java.util.Random;

import uk.ac.keele.csc20004.hw.Parameters;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.HardwarePartFactory;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;
import uk.ac.keele.csc20004.hw.products.PC;
import uk.ac.keele.csc20004.hw.products.Workstation;

/**
 * This class is provided only as an example, to show you a possible use of
 * the classes defining the hardware parts and products.
 * It is only provided as reference.
 * It is *not* part of the coursework and you *should not* extend from it to 
 * complete the assessment. However, feel free to re-use code from it.
 * 
 * This class provided an extremely simplified implementation of the functionalities
 * of a ComputerFactory that *does not* meet all the requirements for the coursework.
 * However, it does show you an example of the expected textual output that you 
 * should provide in your implementation.
 */
public class ToyComputerfactoryDemo {
    public static void main(String[] args) {
        // create random object
        Random r = new Random();

        HardwarePartFactory f = HardwarePartFactory.getFactory();
        ToyComputerFactory shop = new ToyComputerFactory();

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

            String[] choices = { "PC", "WorkStation" };
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
                        Computer pc = new PC(mb, gpu, ram1, ram2);

                        // here, you can use r.nextBoolean() to generate a random
                        // boolean to decide if you want to add "Deluxe" packaging

                        // I'm simulating the production time by sending this thread to sleep()
                        // this might be moved to a more appropriate thread when needed
                        Thread.sleep(pc.getProductionTime());

                        shop.enqueuePackagedComputer(pc);

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
                        Computer ws = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);

                        // here, you can use r.nextBoolean() to generate a random
                        // boolean to decide if you want to add "Deluxe" packaging

                        // I'm simulating the production time by sending this thread to sleep()
                        // this might be moved to a more appropriate thread when needed
                        Thread.sleep(ws.getProductionTime());

                        shop.enqueuePackagedComputer(ws);
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
