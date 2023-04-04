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

    private void setDataToStoreOrders() {
        if (mainController.getOrderTracker().getOrdersInTracker() != null) {
            storeOrders = FXCollections.observableArrayList(mainController.getOrderTracker().getOrdersInTracker());
            storeOrdersListView.setItems(storeOrders);
        }
    }

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

    @FXML
    protected void onExportOrdersButtonClick() {
            exportOrdersLabel.setText(mainController.getOrderTracker().exportStoreOrders());
    }


}