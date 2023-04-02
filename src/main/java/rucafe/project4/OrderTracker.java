package rucafe.project4;

import java.util.ArrayList;

public class OrderTracker {

    private ArrayList<Order> orderTracker;

    /**
     * Adds an order to the list of orders, assigning an order number based on its position in the list.
     * @param order Order: the order being added.
     */
    public void add(Order order) {
        order.setOrderNumber(orderTracker.size() + 1);
        orderTracker.add(order);
    }

    /**
     * Cancels (removes) the order from the list of orders. The order numbers are reassigned to the other orders
     * in the list to account for the removal of the order.
     * @param orderNumber int: the order number that is being removed
     */
    public void cancel(int orderNumber) {
        orderTracker.remove(orderNumber - 1);

        for (int i = 0; i < orderTracker.size(); i++) {
            orderTracker.get(i).setOrderNumber(i + 1);
        }
    }

    /**
     * Prints all orders in the list.
     * @return String: returns the string which contains the printed list of formatted orders.
     */
    public String printOrders() {
        String ret = "";
        for (int i = 0; i < orderTracker.size(); i++)
            ret += (orderTracker.get(i).toString() + "\n\n");

        return ret;
    }
}
