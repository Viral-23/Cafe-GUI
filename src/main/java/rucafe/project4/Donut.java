package rucafe.project4;

/**
 * This is a class that extends the MenuItem class, which allows for the creation of a donut object.
 * @author Viral Patel, Rohan Patel
 */
public abstract class Donut extends MenuItem {
    private String flavor;

    /**
     * Constructor for donut object, contains flavor.
     * @param flavor String: flavor of the donut.
     */
    public Donut(String flavor) {
        this.flavor = flavor;
    }

    /**
     * Getter method to retrieve the flavor of a donut.
     * @return String: returns the flavor of the donut.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Method from parent class MenuItem. It is also abstract in the donut class as donut is an abstract class
     * with three subclasses.
     * @return double: returns the price of the donut.
     */
    public abstract double itemPrice();
}
