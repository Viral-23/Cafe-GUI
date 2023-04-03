package rucafe.project4;

import java.util.ArrayList;

/**
 * This is a class that keeps track of a customers order and order number.
 * @author Viral Patel, Rohan Patel
 */

public class Order {
    private int orderNumber = 0;
    private ArrayList<MenuItem> itemsInOrder;

    public Order() {
        itemsInOrder = new ArrayList<>();
    }

    /**
     * Constructor for the order object, which contains the order number and list of menu items in the order.
     * @param orderNumber int: The order number of the current customer.
     * @param itemsInOrder Object: The list of menu items (donut and coffee) in the order.
     */
    public Order(int orderNumber, ArrayList<MenuItem> itemsInOrder) {
        this.orderNumber = orderNumber;
        this.itemsInOrder = itemsInOrder;
    }

    /**
     * Setter method, which sets the order number of the order to the specified order number.
     * @param orderNumber int: the order number that will be assigned to the order.
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Getter method, which retrieves the order number of the order.
     * @return int: returns the order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<MenuItem> getItemsInOrder() {
        return itemsInOrder;
    }

    /**
     * Adds a menu item to the order, specified by menuItem.
     * @param menuItem
     */
    public void addItemToOrder(Coffee menuItem) {
        itemsInOrder.add(menuItem);
    }

    /**
     * Overrides the toString method, displays items in order and their prices in a list view.
     * @return String: the string that contains the list of formatted items in the order.
     */
    @Override
    public String toString() {
        String ret = "";

        for (MenuItem menuItem : itemsInOrder) {
            String order = menuItem.toString();
            String[] parts = order.split(":");
            String item = parts[0].trim();
            String price = parts[1].trim();
            ret += String.format("%-10s %6s\n", item, price);
        }

        return ret;
    }
}
