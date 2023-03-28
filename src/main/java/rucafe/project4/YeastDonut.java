package rucafe.project4;

/**
 * This is a class that extends the Donut class, which allows for the creation of a yeast donut object.
 * @author Viral Patel, Rohan Patel
 */
public class YeastDonut extends Donut {

    /**
     * Constructor for yeast donut object, contains flavor.
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
}
