package rucafe.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RUCafeMainController {

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
}