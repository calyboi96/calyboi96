package uk.ac.keele.csc20004.task1;

import uk.ac.keele.csc20004.hw.products.Computer;
//Extension of the decorator, works out the cost of a regular computer, then increases it's value by 20% if it has Deluxe Packaging   
public class Deluxe extends Decorator {
    public Deluxe(Computer computer){
        super(computer);
    }
    @Override
    public float getCost() {
        float Cost = wrappedComputer.getCost();
        Cost += 0.2 * Cost;
        return Cost;    }

    @Override 
    public int getProductionTime(){
        return Math.round(getCost());     
    }
    @Override
    public String toString(){
        return "Deluxe" + wrappedComputer.toString();
    }
}
