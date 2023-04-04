package rucafe.project4;

/**
 * This is an abstract class which is the superclass of all menu items.
 * @author Viral Patel, Rohan Patel
 */
public abstract class MenuItem {

    private int quantity;

    public MenuItem() {
        quantity = Constants.DEFAULT_VALUE;
    }
    public void setQuantity(int num) {
        quantity = num;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Abstract method which calculates the price of a menu item. All MenuItem subclasses implement this class,
     * each having their own price based on the item.
     * @return double: returns the price of the item.
     */
    public abstract double itemPrice(); //subclasses must implement this method
}
