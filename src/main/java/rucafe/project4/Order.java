package rucafe.project4;

import java.util.ArrayList;

/**
 * This is a class that keeps track of a customers order and order number.
 * @author Viral Patel, Rohan Patel
 */

public class Order {
    private int orderNumber;
    private ArrayList<Object> itemsInOrder;

    /**
     * Constructor for the order object, which contains the order number and list of menu items in the order.
     * @param orderNumber int: The order number of the current customer.
     * @param itemsInOrder Object: The list of menu items (donut and coffee) in the order.
     */
    public Order(int orderNumber, ArrayList<Object> itemsInOrder) {
        this.orderNumber = orderNumber;
        this.itemsInOrder = itemsInOrder;
    }
}
