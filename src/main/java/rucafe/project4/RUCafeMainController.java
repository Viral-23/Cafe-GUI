package rucafe.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is a class that handles the main view, which allows the user to navigate between different views like the
 * donut view, coffee view, basket view, and orders view.
 * @author Viral Patel, Rohan Patel
 */

public class RUCafeMainController {

    private Order order = new Order();
    private OrderTracker orderTracker = new OrderTracker();

    /**
     * Getter method, retrieves the order stored in the main controller.
     * @return Order: returns the order stored in the main controller.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Clears the current order, so that a new order can be made by the user.
     */
    public void resetOrder() {
        order = new Order();
    }

    /**
     * Getter method, retrieves the orders stored in the main controller.
     * @return OrderTracker: returns the OrderTracker object which holds all the customer orders placed.
     */
    public OrderTracker getOrderTracker() {
        return orderTracker;
    }

    /**
     * Set-up for the donut view. The reference to the main controller is passed here.
     */
    @FXML
    protected void displayDonutsView() {
        Stage donutView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonutsView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 1000, 700);
            donutView.setTitle("Donut View");
            donutView.setScene(scene);
            donutView.show();
            OrderingDonutsController donutsController = loader.getController();
            donutsController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingDonutsView.fxml.");
            alert.setContentText("Couldn't load OrderingDonutsView.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Set-up for the coffee view. The reference to the main controller is passed here.
     */
    @FXML
    protected void displayCoffeeView() {
        Stage coffeeView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffeeView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 1000, 700);
            coffeeView.setTitle("Coffee View");
            coffeeView.setScene(scene);
            coffeeView.show();
            OrderingCoffeeController coffeeController = loader.getController();
            coffeeController.setMainController(this);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingCoffeeView.fxml.");
            alert.setContentText("Couldn't load OrderingCoffeeView.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Set-up for the basket view. The reference to the main controller is passed here.
     */
    @FXML
    protected void displayBasketView() {
        Stage basketView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingBasketView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 1000, 700);
            basketView.setTitle("Basket View");
            basketView.setScene(scene);
            basketView.show();
            OrderingBasketController basketController = loader.getController();
            basketController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingBasketView.fxml.");
            alert.setContentText("Couldn't load OrderingBasketView.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Set-up for the store orders view. The reference to the main controller is passed here.
     */
    @FXML
    protected void displayOrdersView() {
        Stage ordersView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 1000, 700);
            ordersView.setTitle("Store Orders View");
            ordersView.setScene(scene);
            ordersView.show();
            StoreOrdersController ordersController = loader.getController();
            ordersController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading StoreOrdersView.fxml.");
            alert.setContentText("Couldn't load StoreOrdersView.fxml.");
            alert.showAndWait();
        }
    }
}