package uk.ac.keele.csc20004.task2;

import java.util.ArrayList;

import uk.ac.keele.csc20004.hw.parts.HardwarePartFactory;
import uk.ac.keele.csc20004.hw.products.Computer;
import uk.ac.keele.csc20004.task1.FactoryShelf;

public class ThreadDemo {

    public static void main(String[] args){
        
        final FactoryShelf mbStorage;
        final FactoryShelf gpuStorage;
        final FactoryShelf ramStorage;
        final ThreadFactory pcFactory;
        final ThreadFactory wsFactory;
        final ThreadFactory lpFactory;
        final HardwarePartFactory f;
        final ArrayList<Computer> packageQueue;
    
        mbStorage = new FactoryShelf();
        gpuStorage = new FactoryShelf();
        ramStorage = new FactoryShelf();
        packageQueue = new ArrayList<>();
        pcFactory = new ThreadFactory(mbStorage, gpuStorage, ramStorage, packageQueue);
        wsFactory = new ThreadFactory(mbStorage, gpuStorage, ramStorage, packageQueue);
        lpFactory = new ThreadFactory(mbStorage, gpuStorage, ramStorage, packageQueue);
        f = HardwarePartFactory.getFactory();
        

        ShelfManager shelfmanager = new ShelfManager(mbStorage, gpuStorage, ramStorage, f);
        FactoryManager factorymanager = new FactoryManager(mbStorage, gpuStorage, ramStorage, pcFactory, wsFactory, lpFactory, packageQueue);
        ThreadStore threadstore = new ThreadStore(packageQueue);

        shelfmanager.begin();
        factorymanager.begin();
        threadstore.start();
        threadstore.setName("ThreadStore"); 
    }
    
}

