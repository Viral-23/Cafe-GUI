package rucafe.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


/**
 * This is a class that handles the basket view, which allows the user to view and edit their basket before placing
 * their order.
 * @author Viral Patel, Rohan Patel
 */
public class OrderingBasketController {
    private RUCafeMainController mainController;

    private ObservableList<MenuItem> itemsInOrder;
    @FXML
    private Label removalConfirmationText, orderPlacedConfirmation, taxLabel, subtotalLabel, totalLabel;
    @FXML
    private ListView<MenuItem> itemsInCustomerOrder;

    /**
     * Get the reference to the MainController object. Also initializes the listview based on the order stored in
     * the MainController object.
     * @param controller: the reference to the MainController object.
     */
    public void setMainController (RUCafeMainController controller){
        mainController = controller;
        setDataToBasket();
    }

    /**
     * Sets the order and costs when the user enters the basket view.
     */
    public void setDataToBasket() {
        if (mainController.getOrder().getItemsInOrder() != null) {
            itemsInOrder = FXCollections.observableArrayList(mainController.getOrder().getItemsInOrder());
            itemsInCustomerOrder.setItems(itemsInOrder);
            updateCosts();
        }
    }

    /**
     * Updates the costs, including the sales tax, subtotal, and total. Called in other methods when changes
     * to the order are made.
     */
    private void updateCosts() {
        taxLabel.setText("Sales tax: $" + String.format("%.2f", mainController.getOrder().calculateSalesTax()));
        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", mainController.getOrder().calculateSubtotal()));
        totalLabel.setText("Total: $"  + String.format("%.2f",
                mainController.getOrder().calculateSalesTax() + mainController.getOrder().calculateSubtotal()));
    }

    /**
     * Clears the labels for any costs, used when an order is placed.
     */
    private void clearCosts() {
        taxLabel.setText("");
        subtotalLabel.setText("");
        totalLabel.setText("");
    }

    /**
     * Handles the Remove Item button. If an item is selected in the listview and the button is pressed, the item
     * will be removed and a confirmation text will appear. If the button is pressed without selecting an item,
     * an error message will appear instead.
     */
    @FXML
    protected void onRemoveItemClick() {
        int selectedIndex = itemsInCustomerOrder.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            itemsInOrder.remove(selectedIndex);
            mainController.getOrder().removeItemInOrder(selectedIndex);
            removalConfirmationText.setText("Item successfully removed.");
            itemsInCustomerOrder.getSelectionModel().clearSelection();
            updateCosts();
        }
        else if (!itemsInCustomerOrder.getItems().isEmpty())
            removalConfirmationText.setText("No item selected.");
        else
            removalConfirmationText.setText("No items in basket.");
    }

    /**
     * Handles the Place Order button. When this button is pressed, the OrderTracker in the main controller gets the
     * current order added to it. Then the order is reset and any information pertaining to that order is also
     * cleared. A confirmation text is displayed if this action is successful, otherwise an error message appears.
     */
    @FXML
    protected void onPlaceOrderButtonClick() {
        if (!itemsInCustomerOrder.getItems().isEmpty()) {
            Order order = new Order(mainController.getOrder());
            mainController.getOrderTracker().add(order);
            orderPlacedConfirmation.setText("Order successfully placed, your order number is: "
                    + order.getOrderNumber());
            mainController.resetOrder();
            itemsInCustomerOrder.getItems().clear();
            clearCosts();
        }
        else
            orderPlacedConfirmation.setText("No order in basket.");
    }

}