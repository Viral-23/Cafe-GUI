package rucafe.project4;

/**
 * This is a class that extends the Donut class, which allows for the creation of a cake donut object.
 * @author Viral Patel, Rohan Patel
 */
public class CakeDonut extends Donut {

    /**
     * Constructor for cake donut object, contains flavor.
     * @param flavor String: flavor of the donut.
     */
    public CakeDonut(String flavor) {
        super(flavor);
    }

    /**
     * Calculates the price of a cake donut.
     * @return double: returns the price of the cake donut.
     */
    @Override
    public double itemPrice() {
        return Constants.CAKE_DONUT_PRICE;
    }

    /**
     * Overrides the toString method, which formats the cake donut object.
     * @return String: returns the formatted cake donut object, which includes the flavor and price.
     */
    @Override
    public String toString() {
        return "Cake Donut (" + super.getFlavor() + "): $" + itemPrice();
    }
}
