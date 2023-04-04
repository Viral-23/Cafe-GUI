package rucafe.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * This is a class that handles the store orders view, which allows the user to view and cancel orders.
 * @author Viral Patel, Rohan Patel
 */
public class StoreOrdersController {
    private RUCafeMainController mainController;
    private ObservableList<Order> storeOrders;

    @FXML
    private ListView<Order> storeOrdersListView;

    @FXML
    private Label cancelOrderLabel, exportOrdersLabel;

    /**
     * Get the reference to the MainController object. Also initializes the listview based on the OrderTracker
     * stored in the MainController object.
     * @param controller: the reference to the MainController object.
     */
    public void setMainController (RUCafeMainController controller){
        mainController = controller;
        setDataToStoreOrders();
    }

    /**
     * Sets the store orders when the user enters the store orders view.
     */
    private void setDataToStoreOrders() {
        if (mainController.getOrderTracker().getOrdersInTracker() != null) {
            storeOrders = FXCollections.observableArrayList(mainController.getOrderTracker().getOrdersInTracker());
            storeOrdersListView.setItems(storeOrders);
        }
    }

    /**
     * Handles the Cancel Order button. When this button is clicked, a selected order is cancelled and removed from
     * the list. The order numbers are reassigned based on the deletion. If no order is selected, an error message
     * will appear.
     */
    @FXML
    protected void onCancelOrderButtonClick() {
        int selectedIndex = storeOrdersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            storeOrders.remove(selectedIndex);
            mainController.getOrderTracker().cancel(selectedIndex);
            cancelOrderLabel.setText("Order successfully cancelled.");
            storeOrdersListView.getSelectionModel().clearSelection();
        }
        else if (!storeOrdersListView.getItems().isEmpty())
            cancelOrderLabel.setText("No order selected.");
        else
            cancelOrderLabel.setText("No store orders.");
    }

    /**
     * Handles the Export Orders button. When this button is clicked, a message will appear based on the action
     * that is taken. If the order is successfully exported, it will display that and show in the storeOrders.txt
     * file. Otherwise, it will show the relevant error message.
     */
    @FXML
    protected void onExportOrdersButtonClick() {
            exportOrdersLabel.setText(mainController.getOrderTracker().exportStoreOrders());
    }


}