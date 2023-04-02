package rucafe.project4;

import java.util.ArrayList;

/**
 * This is a class that allows for the creation of a coffee object.
 * @author Viral Patel, Rohan Patel
 */
public class Coffee extends MenuItem {
    private String cupSize;
    private ArrayList<String> addIns;

    /**
     * Constructor for the Coffee object. Creates a coffee object, which contains a cupSize and a list of add-ins.
     * @param cupSize: String that determines the cup size for coffee.
     * @param addIns: List of strings that contains all add-ins for the coffee.
     */
    public Coffee(String cupSize, ArrayList<String> addIns) {
        this.cupSize = cupSize;
        this.addIns = addIns;
    }

    /**
     * Getter method to retrieve the cup size of a coffee object.
     * @return String: returns the cup size for the coffee.
     */
    public String getCupSize() {
        return cupSize;
    }

    /**
     * Getter method to retrieve the add-ins for a coffee object.
     * @return ArrayList: returns list of strings which are the add-ins for the coffee.
     */
    public ArrayList<String> getAddIns() {
        return addIns;
    }

    public double itemPrice() {
        double total = 0;
        switch (cupSize) {
            case "Short":
                total = Constants.SHORT_CUP_COST;
            case "Tall":
                total = Constants.TALL_CUP_COST;
            case "Grande":
                total = Constants.GRANDE_CUP_COST;
            case "Venti":
                total = Constants.VENTI_CUP_COST;
            default:
                total = Constants.NO_COST;
        }

        total += (addIns.size() * Constants.ADD_IN_COST);

        return total;
    }

    /**
     * Overrides the toString method to display the Coffee object in the form of a string.
     * @return String: returns the Coffee object formatted as a String, which includes the cup size, list of add-ins,
     * and the price.
     */
    @Override
    public String toString() {
        String ret = cupSize + " black coffee";

        if (addIns.size() > 0) {
            ret += " with ";
            for (int i = 0; i < addIns.size(); i++) {
                ret += addIns.get(i);

                if (addIns.size() > 1) {
                    ret += ", ";
                    if (i == addIns.size() - 2)
                        ret += "and ";
                }
            }
        }

        return ret + ": $" + itemPrice();
    }
}
