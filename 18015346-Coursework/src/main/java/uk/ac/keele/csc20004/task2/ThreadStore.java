package uk.ac.keele.csc20004.task2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import uk.ac.keele.csc20004.hw.products.Computer;

public class ThreadStore extends Thread{

    static ArrayList<Computer> packagequeue;

    static Random r = new Random();
    
    public ThreadStore(ArrayList<Computer> packageQueue){
        packagequeue = packageQueue;
    }

    public void run(){
        while(true){
            // sell them
            synchronized(packagequeue){
                if(packagequeue.size() > 0){
                    Computer c = packagequeue.stream().max(Comparator.comparing(Computer::getCost)).get();
                    packagequeue.remove(packagequeue.indexOf(c));
    
                    System.out.println("Sold: " + c + " - cost: " + c.getCost());
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
