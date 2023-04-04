package rucafe.project4;

/**
 * This is a class that extends the Donut class, which allows for the creation of a yeast donut object.
 * @author Viral Patel, Rohan Patel
 */
public class YeastDonut extends Donut {

    /**
     * Constructor for yeast donut object, contains flavor. Also contains a quantity initialized to one in the
     * superclass MenuItem.
     * @param flavor String: flavor of the donut.
     */
    public YeastDonut(String flavor) {
        super(flavor);
    }

    /**
     * Calculates the price of a yeast donut.
     * @return double: returns the price of the yeast donut.
     */
    @Override
    public double itemPrice() {
        return Constants.YEAST_DONUT_PRICE;
    }

    /**
     * Overrides the toString method, which formats the yeast donut object.
     * @return String: returns the formatted yeast donut object, which includes the quantity, flavor, and price.
     */
    @Override
    public String toString() {
        return super.getQuantity() + "x: " + "Yeast Donut (" + super.getFlavor() + "): $" + itemPrice();
    }
}
