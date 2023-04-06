package rucafe.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This is a class that handles the donut view, which allows the user to customize and add donuts to their basket.
 * @author Viral Patel, Rohan Patel
 */

public class OrderingDonutsController {
    private RUCafeMainController mainController;

    private boolean isDonutSelected = false;
    private boolean isFlavorSelected = false;
    private boolean isQuantitySelected = false;

    String donutType, flavor;
    int quantity;
    Donut donut = null;
    @FXML
    FileInputStream inputStreamYeastDonut = new
            FileInputStream("src/main/resources/rucafe/project4/Yeast Donut.jpeg");
    @FXML
    Image yeastDonutImage = new Image(inputStreamYeastDonut);
    @FXML
    FileInputStream inputStreamCakeDonut = new
            FileInputStream("src/main/resources/rucafe/project4/Cake Donut.png");
    @FXML
    Image cakeDonutImage = new Image(inputStreamCakeDonut);
    @FXML
    FileInputStream inputStreamDonutHole = new
            FileInputStream("src/main/resources/rucafe/project4/Donut Hole.jpeg");

    @FXML
    Image donutHoleImage = new Image(inputStreamDonutHole);

    @FXML
    private ComboBox<String> donutTypeSelector;

    @FXML
    private Label donutTypeLabel, subtotalLabel, addToBasketConfirmation, quantityConfirmationText, flavorLabel;

    @FXML
    private ImageView donutImage;

    @FXML
    private ListView flavorList;

    @FXML
    private TextFlow donutOrder;

    @FXML
    private ScrollPane donutOrderScroll;

    @FXML
    private TextField quantityInput;

    @FXML
    private Button quantitySetButton;

    /**
     * Constructor for the class, throws FileNotFoundException. This is necessary in order to set up the images
     * for the ImageView.
     * @throws FileNotFoundException: the exception being thrown.
     */
    public OrderingDonutsController() throws FileNotFoundException {
    }

    /**
     * Get the reference to the MainController object.
     * @param controller: the reference to the MainController object.
     */
    public void setMainController (RUCafeMainController controller){
        mainController = controller;
    }

    /**
     * Initializes upon calling the donuts view. Sets up the ComboBox with the donut types available. Quantity
     * selection is disabled initially, as you cannot select a quantity without specifying a donut type and flavor
     * first.
     */
    @FXML
    public void initialize() {
        String donutTypeOptions[] = {"Yeast Donut", "Cake Donut", "Donut Hole"};
        donutTypeSelector.getItems().addAll(donutTypeOptions);
        donutTypeLabel.setText("No option selected.");
        donutOrderScroll.setContent(donutOrder);
        disableQuantityInput();
    }

    /**
     * Event handler for the donut type selection ComboBox. Each time the donut type is changed, it displays
     * a confirmation text telling the user which donut is selected. Then, an image is displayed based on which
     * donut type is chosen. Finally, the list of flavors is displayed for that donut type. Note that everytime
     * this handler is called, the quantity inputs are disabled because the flavor will be deselected and both
     * type and flavor must be selected before being able to specify a quantity. The donut type will be stored
     * for later use.
     * @param event: the action taken by the user.
     */
    @FXML
    protected void donutSelected(ActionEvent event) {
        String selected = donutTypeSelector.getSelectionModel().getSelectedItem();
        donutTypeLabel.setText(selected + " selected.");
        donutType = donutTypeSelector.getValue();
        switchDonutImage(donutType);
        updateFlavorsList(donutType);
        flavorLabel.setText("");
        disableQuantityInput();
        isDonutSelected = true;

        calculateAndSetSubtotal();
    }

    /**
     * Helper method which switches the image in the ImageView based on the donut type specified.
     * @param donutType String: the donut type selected by the user.
     */
    private void switchDonutImage(String donutType) {
        switch (donutType) {
            case "Yeast Donut": {
                donutImage.setImage(yeastDonutImage);
                break;
            }
            case "Cake Donut": {
                donutImage.setImage(cakeDonutImage);
                break;
            }
            case "Donut Hole": {
                donutImage.setImage(donutHoleImage);
                break;
            }
        }
    }

    /**
     * Helper method which updates the list of flavors based on the type of donut that is chosen. The new list of
     * flavors is displayed in the ListView after this method is called.
     * @param donutType String: the type of donut.
     */
    private void updateFlavorsList(String donutType) {
        String[] yeastDonutFlavors = {"Glazed", "Chocolate Frosted",
                "Strawberry Frosted", "Custard", "Apple Cinnamon", "Jelly"};
        String[] cakeDonutFlavors = {"Plain", "Chocolate", "Blueberry"};
        String[] donutHoleFlavors = {"Glazed", "Chocolate", "Jelly", "Powdered"};
        ObservableList<String> flavors = FXCollections.observableArrayList();

        switch (donutType) {
            case "Yeast Donut": {
                flavors = FXCollections.observableArrayList(yeastDonutFlavors);
                break;
            }
            case "Cake Donut": {
                flavors = FXCollections.observableArrayList(cakeDonutFlavors);
                break;
            }
            case "Donut Hole": {
                flavors = FXCollections.observableArrayList(donutHoleFlavors);
                break;
            }
        }
        flavorList.setItems(flavors);
    }

    /**
     * Handler for the ListView of flavors. When a flavor is chosen (clicked on, which selects it) a confirmation
     * message is displayed to notify the user of their choice. The flavor is stored for later use.
     */
    @FXML
    protected void flavorSelected() {
        flavor = (String) flavorList.getSelectionModel().getSelectedItem();
        flavorLabel.setText(flavor + " selected.");
        enableQuantityInput();
        isFlavorSelected = true;

        calculateAndSetSubtotal();
    }

    /**
     * Handler for the quantity TextField and set button. Any time the quantity is entered (either by pressing
     * enter on the keyboard or clicking the set button) a confirmation text will display. If the input is valid
     * the quantity will be updated accordingly, if the input is not valid the corresponding error message will
     * display notifying the user what is wrong.
     */
    @FXML
    protected void quantitySelected() {
        String input = quantityInput.getText();
        String confirmation = getQuantityConfirmationMessage(input);
        quantityConfirmationText.setText(confirmation);

        calculateAndSetSubtotal();
    }

    /**
     * Method that adds the created donut order to the basket. A confirmation text is displayed if the donut order is
     * successfully added to the basket, otherwise the corresponding error message will appear notifying the
     * user what actions need to be taken before the donut can be added to the basket.
     */
    @FXML
    protected void onAddToBasketClick() {
        if (isDonutSelected && isFlavorSelected && isQuantitySelected) {
            addToBasketConfirmation.setText("Order added to basket.");
            mainController.getOrder().addItemToOrder(donut);
        }
        else {
            if (!isDonutSelected)
                addToBasketConfirmation.setText("Please select a type of donut.");
            else if (!isFlavorSelected)
                addToBasketConfirmation.setText("Please select a flavor for the donut.");
            else
                addToBasketConfirmation.setText("Please specify quantity.");
        }
    }

    /**
     * Helper method which determines what confirmation/error message to display based on the user input for
     * quantity. If the quantity is valid, it is stored for later use.
     * @param input String: the user inputted quantity.
     * @return String: returns the message which will be displayed to the user.
     */
    private String getQuantityConfirmationMessage(String input) {
        if (input.isEmpty()) {
            isQuantitySelected = false;
            return "Please input a quantity.";
        }
        int tempQuantity = Constants.NOT_FOUND;
        try {
            tempQuantity = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            isQuantitySelected = false;
            return "Invalid input, not an integer.";
        }
        if (tempQuantity < Constants.MIN_DONUT_QUANTITY || tempQuantity > Constants.MAX_DONUT_QUANTITY) {
            isQuantitySelected = false;
            return ("Quantity out of range, please enter a valid number between " +
                    Constants.MIN_DONUT_QUANTITY + " and " + Constants.MAX_DONUT_QUANTITY);
        }

        isQuantitySelected = true;
        quantity = Integer.parseInt(input);
        return "Quantity: " + quantity;
    }

    /**
     * Method that calculates and displays the subtotal of the donut order created. This method is called in every
     * handler, but it only truly executes if all flags are true. The flags are determined by if a donut, flavor,
     * and quantity are selected/provided.
     */
    @FXML
    private void calculateAndSetSubtotal() {
        if (isDonutSelected && isFlavorSelected && isQuantitySelected) {
            donutOrder.getChildren().clear();

            donut = createDonut(donutType);

            donut.setFlavor(flavor);
            donut.setQuantity(quantity);

            String donutOrderString = donut.toString();
            String[] donutOrderParts = donutOrderString.split("\\$");
            double itemPrice = Double.parseDouble(donutOrderParts[1]) / quantity;
            String newDonutOrderString = donutOrderParts[0] + "$" + String.format("%.2f", itemPrice);

            donutOrder.getChildren().add(new Text(newDonutOrderString));

            subtotalLabel.setText("Subtotal:\t$" + String.format("%.2f", donut.itemPrice()));
        }
        else {
            donutOrder.getChildren().clear();
            subtotalLabel.setText("");
        }

    }

    /**
     * Helper method which enables all quantity related controls. This includes the TextField where the user
     * enters the quantity and the Set button. This method is used when a flavor is selected, allowing the user
     * to input a quantity.
     */
    private void enableQuantityInput() {
        quantityInput.setDisable(false);
        quantitySetButton.setDisable(false);
    }

    /**
     * Helper method which disables all quantity related controls. This includes the TextField where the user
     * enters the quantity and the Set button. When this method is called, it also clears any previous entries that
     * the user typed prior, to maintain a clean look if the user happens to switch donut types.
     */
    private void disableQuantityInput() {
        quantityInput.clear();
        quantityInput.setDisable(true);
        quantitySetButton.setDisable(true);
        quantityConfirmationText.setText("");

        isFlavorSelected = false;
        isQuantitySelected = false;
        flavor = null;
        quantity = Constants.NOT_FOUND;
    }

    /**
     * Helper method which creates a donut object based on the type of donut that was selected.
     * @param donutType String: the type of donut selected in the ComboBox.
     * @return Donut: returns the donut object once it is created.
     */
    private Donut createDonut(String donutType) {
        switch (donutType) {
            case "Yeast Donut": {
                donut = new YeastDonut();
                break;
            }
            case "Cake Donut": {
                donut = new CakeDonut();
                break;
            }
            case "Donut Hole": {
                donut = new DonutHole();
                break;
            }
        }
        return donut;
    }
}