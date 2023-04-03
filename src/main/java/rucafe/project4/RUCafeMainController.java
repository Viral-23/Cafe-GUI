package rucafe.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RUCafeMainController {

    private Order order = new Order();
    private Coffee coffee = new Coffee();
    public Order getOrder() {
        return order;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void resetCoffee() {
        coffee = new Coffee();
    }


    @FXML
    protected void displayDonutsView() {
        Stage donutView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonutsView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 500, 500);
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

    @FXML
    protected void displayCoffeeView() {
        Stage coffeeView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffeeView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 1000, 700);
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

    @FXML
    protected void displayBasketView() {
        Stage basketView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingBasketView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 500, 500);
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

    @FXML
    protected void displayOrdersView() {
        Stage ordersView = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 500, 500);
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