package rucafe.project4;

/**
 * This is a class that extends the Donut class, which allows for the creation of a donut hole object.
 * @author Viral Patel, Rohan Patel
 */
public class DonutHole extends Donut {

    /**
     * Constructor for donut hole object, contains flavor.
     * @param flavor String: flavor of the donut.
     */
    public DonutHole(String flavor) {
        super(flavor);
    }

    /**
     * Calculates the price of a donut hole.
     * @return double: returns the price of the donut hole.
     */
    @Override
    public double itemPrice() {
        return Constants.DONUT_HOLE_PRICE;
    }

    @Override
    public String toString() {
        return "Donut Hole (" + super.getFlavor() + "): $" + itemPrice();
    }
}
