package uk.ac.keele.csc20004.task2;
import java.util.ArrayList;
import java.util.Random;
import uk.ac.keele.csc20004.hw.parts.GPU;
import uk.ac.keele.csc20004.hw.parts.MotherBoard;
import uk.ac.keele.csc20004.hw.parts.RAM;
import uk.ac.keele.csc20004.hw.products.Computer;
import uk.ac.keele.csc20004.hw.products.PC;
import uk.ac.keele.csc20004.hw.products.Workstation;
import uk.ac.keele.csc20004.task1.Deluxe;
import uk.ac.keele.csc20004.task1.Laptop;
import uk.ac.keele.csc20004.task1.FactoryShelf;


public class FactoryManager{
    static FactoryShelf mbstorage;
    static FactoryShelf gpustorage;
    static FactoryShelf ramstorage;
    static ThreadFactory pcfactory;
    static ThreadFactory wsfactory;
    static ThreadFactory lpfactory;
    static ArrayList<Computer> packagequeue;

    static Random r = new Random();

    public FactoryManager(FactoryShelf mbStorage, FactoryShelf gpuStorage, FactoryShelf ramStorage, ThreadFactory PCFactory, ThreadFactory WSFactory, ThreadFactory LPFactory, ArrayList<Computer> packageQueue) {
        mbstorage = mbStorage;
        gpustorage = gpuStorage;
        ramstorage = ramStorage;
        pcfactory = PCFactory;
        wsfactory = WSFactory;
        lpfactory = LPFactory;
        packagequeue = packageQueue;
    }

    
    public static class PCFactoryThread extends Thread{

        public void run(){
            MotherBoard mb = new MotherBoard(0);
            RAM ram1 = new RAM(0);
            RAM ram2 = new RAM(0);
            GPU gpu = new GPU(0);

            Computer pc = new PC(mb, gpu, ram1, ram2);
            Computer deluxePC = new PC(mb, gpu, ram1, ram2);
            
            boolean gotParts = false;
            boolean gotRAM = false;
            boolean gotGPU = false;
            boolean gotMB = false;
            boolean deluxe = false;

            while(true){
                while(gotParts == false){
                    if(gotRAM == false){
                        synchronized(ramstorage){
                            if(ramstorage.size() >= 2){
                                ram1 = (RAM)ramstorage.retrievePart();
                                ram2 = (RAM)ramstorage.retrievePart();
                                gotRAM = true;
                                System.out.println("(DEBUG)PC GOT RAM");
                            }
                        }
                    }
                    if(gotGPU == false){
                        synchronized(gpustorage){
                            if(gpustorage.size() >= 1){
                                gpu = (GPU)gpustorage.retrievePart();
                                gotGPU = true;
                                System.out.println("(DEBUG)PC GOT GPU");
                            }
                        }
                    }
                    if(gotMB == false){
                        synchronized(mbstorage){
                            if(mbstorage.size() >= 1){
                                mb = (MotherBoard)mbstorage.retrievePart();
                                gotMB = true;
                                System.out.println("(DEBUG)PC GOT MOTHERBOARD");
                            }
                        }
                    }
                    if(gotRAM && gotGPU && gotMB == true){
                        gotParts = true;
                    }        
                }

                try {
                    if(r.nextBoolean()){
                        pc = new PC(mb, gpu, ram1, ram2);
                        deluxePC = new Deluxe(pc);
                        Thread.sleep(deluxePC.getProductionTime());
                        deluxe = true;
                        System.out.println("(DEBUG)BUILT A DELUXE PC");
                        
                    } else {
                        pc = new PC(mb, gpu, ram1, ram2);
                        Thread.sleep(pc.getProductionTime());
                        deluxe = false;
                        System.out.println("(DEBUG)BUILT A PC");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }    
                
                synchronized(packagequeue){
                    if(deluxe){
                        pcfactory.enqueuePackagedComputer(deluxePC);
                        gotParts = false;
                        gotRAM = false;
                        gotGPU = false;
                        gotMB = false;
                        System.out.println("(DEBUG)MOVED A DELUXE PC TO STORE");
                    }else{
                        pcfactory.enqueuePackagedComputer(pc);
                        gotParts = false;
                        gotRAM = false;
                        gotGPU = false;
                        gotMB = false;
                        System.out.println("(DEBUG)MOVED A PC TO STORE");
                    }
                }
            }
        }
    }


    public static class WorkStationFactoryThread extends Thread{

        public void run(){
            MotherBoard mb = new MotherBoard(0);
            RAM ram1 = new RAM(0);
            RAM ram2 = new RAM(0);
            RAM ram3 = new RAM(0);
            RAM ram4 = new RAM(0);
            GPU gpu = new GPU(0);

            Computer workstation = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);
            Computer deluxeworkstation = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);
            
            boolean gotParts = false;
            boolean gotRAM = false;
            boolean gotGPU = false;
            boolean gotMB = false;
            boolean deluxe = false;

            while(true){
                while(gotParts == false){
                    if(gotRAM == false){
                        synchronized(ramstorage){
                            if(ramstorage.size() >= 4){
                                ram1 = (RAM)ramstorage.retrievePart();
                                ram2 = (RAM)ramstorage.retrievePart();
                                ram3 = (RAM)ramstorage.retrievePart();
                                ram4 = (RAM)ramstorage.retrievePart();
                                gotRAM = true;
                                System.out.println("(DEBUG)WORKSTATION GOT RAM");
                            }
                        }
                    }
                    if(gotGPU == false){
                        synchronized(gpustorage){
                            if(gpustorage.size() >= 1){
                                gpu = (GPU)gpustorage.retrievePart();
                                gotGPU = true;
                                System.out.println("(DEBUG)WORKSTATION GOT GPU");
                            }
                        }
                    }
                    if(gotMB == false){
                        synchronized(mbstorage){
                            if(mbstorage.size() >= 1){
                                mb = (MotherBoard)mbstorage.retrievePart();
                                gotMB = true;
                                System.out.println("(DEBUG)WORKSTATION GOT MB");
                            }
                        }
                    }
                    if(gotRAM && gotGPU && gotMB == true){
                        gotParts = true;
                    }        
                }

                try {
                    if(r.nextBoolean()){
                        workstation = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);
                        deluxeworkstation = new Deluxe(workstation);
                        Thread.sleep(deluxeworkstation.getProductionTime());
                        deluxe = true;
                        System.out.println("(DEBUG)BUILT A DELUXE WORKSTATION");
                        
                    } else {
                        workstation = new Workstation(mb, gpu, ram1, ram2, ram3, ram4);
                        Thread.sleep(workstation.getProductionTime());
                        deluxe = false;  
                        System.out.println("(DEBUG)BUILT A WORKSTATION");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }    
                
                synchronized(packagequeue){
                    if(deluxe){
                        wsfactory.enqueuePackagedComputer(deluxeworkstation);
                        System.out.println("(DEBUG)MOVED A DELUXE WORKSTATION TO STORE");
                        gotParts = false;
                        gotRAM = false;
                        gotGPU = false;
                        gotMB = false;
                    }else{
                        wsfactory.enqueuePackagedComputer(workstation);
                        gotParts = false;
                        gotRAM = false;
                        gotGPU = false;
                        gotMB = false;
                        System.out.println("(DEBUG)MOVED A WORKSTATION TO STORE");
                    }
                }
            }
        }
    }

    public static class LaptopFactoryThread extends Thread{

        public void run(){
            MotherBoard mb = new MotherBoard(0);
            RAM ram1 = new RAM(0);
            GPU gpu = new GPU(0);

            Computer laptop = new Laptop(mb, gpu, ram1);
            Computer deluxelaptop = new Laptop(mb, gpu, ram1);
            
            boolean gotParts = false;
            boolean gotRAM = false;
            boolean gotGPU = false;
            boolean gotMB = false;
            boolean deluxe = false;

            while(true){
                while(gotParts == false){
                    if(gotRAM == false){
                        synchronized(ramstorage){
                            if(ramstorage.size() >= 1){
                                ram1 = (RAM)ramstorage.retrievePart();
                                gotRAM = true;
                                System.out.println("(DEBUG)LAPTOP GOT RAM");
                            }
                        }
                    }
                    if(gotGPU == false){
                        synchronized(gpustorage){
                            if(gpustorage.size() >= 1){
                                gpu = (GPU)gpustorage.retrievePart();
                                gotGPU = true;
                                System.out.println("(DEBUG)LAPTOP GOT GPU");
                            }
                        }
                    }
                    if(gotMB == false){
                        synchronized(mbstorage){
                            if(mbstorage.size() >= 1){
                                mb = (MotherBoard)mbstorage.retrievePart();
                                gotMB = true;
                                System.out.println("(DEBUG)LAPTOP GOT MB");
                            }
                        }
                    }
                    if(gotRAM && gotGPU && gotMB == true){
                        gotParts = true;
                    }    
                }

                try {
                    if(r.nextBoolean()){
                        laptop = new Laptop(mb, gpu, ram1);
                        deluxelaptop = new Deluxe(laptop);
                        Thread.sleep(deluxelaptop.getProductionTime());
                        deluxe = true;
                        System.out.println("(DEBUG)BUILT A DELUXE LAPTOP");
                        
                    } else {
                        laptop = new Laptop(mb, gpu, ram1);
                        Thread.sleep(laptop.getProductionTime());
                        deluxe = false;  
                        System.out.println("(DEBUG)BUILT A LAPTOP");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }    
                
                synchronized(packagequeue){
                    if(deluxe){
                        lpfactory.enqueuePackagedComputer(deluxelaptop);
                        gotParts = false;
                        gotRAM = false;
                        gotGPU = false;
                        gotMB = false;
                        System.out.println("(DEBUG)MOVED A DELUXE LAPTOP TO STORE");
                    }else{
                        lpfactory.enqueuePackagedComputer(laptop);
                        gotParts = false;
                        gotRAM = false;
                        gotGPU = false;
                        gotMB = false;
                        System.out.println("(DEBUG)MOVED A LAPTOP TO STORE");
                    }
                }
            }
        }
    }
    
    public void begin(){
        PCFactoryThread pcfactorythread = new PCFactoryThread();
        pcfactorythread.start();
        pcfactorythread.setName("PCFactory");
        WorkStationFactoryThread workstationfactorythread = new WorkStationFactoryThread();
        workstationfactorythread.start();
        workstationfactorythread.setName("WorkstationFactory");
        LaptopFactoryThread laptopfactorythread = new LaptopFactoryThread();
        laptopfactorythread.start();
        laptopfactorythread.setName("LaptopFactory");
    }
}
