package rucafe.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

/**
 * This is a class that allows for the creation of a coffee object.
 * @author Viral Patel, Rohan Patel
 */
public class OrderingCoffeeController {
    private RUCafeMainController mainController;

    String cupSize;
    ArrayList<String> addIns = new ArrayList<>();
    double subtotal;

    private boolean cupSizeSelected = false;
    @FXML
    private Label cupSizeConfirmationText, addInsConfirmationText, quantityConfirmationText, subtotalLabel,
            addToBasketConfirmation;
    @FXML
    private ChoiceBox<String> cupSizeSelector, quantitySelection;

    @FXML
    private CheckBox sweetCream, frenchVanilla, irishCream, caramel, mocha;
    @FXML
    private ScrollPane coffeeOrderScroll;
    @FXML
    private TextFlow coffeeOrder;

    public void setMainController (RUCafeMainController controller){
        mainController = controller;
    }


    /**
     * Initializes upon calling the coffee view. Add-ins and quantity selection will be disabled until a cup size
     * is chosen. The cup size options and quantity options are set up here.
     */
    @FXML
    public void initialize() {
        sweetCream.setDisable(true);
        frenchVanilla.setDisable(true);
        irishCream.setDisable(true);
        caramel.setDisable(true);
        mocha.setDisable(true);
        quantitySelection.setDisable(true);

        String cupSizeOptions[] = {"Short", "Tall", "Venti", "Grande"};
        cupSizeSelector.getItems().addAll(cupSizeOptions);
        cupSizeConfirmationText.setText("No option selected.");

        String quantityOptions[] = {"1", "2", "3", "4", "5"};
        quantitySelection.getItems().addAll(quantityOptions);

        coffeeOrderScroll.setContent(coffeeOrder);
    }

    /**
     * Event handler for the cup size drop down menu. When a size is chosen for the first time, the add-ins and
     * quantity menus are unlocked. The cup size selected is displayed and stored and the cost is calculated.
     * @param event: the action taken by the user.
     */
    @FXML
    protected void displayCupSizeSelected(ActionEvent event) {
        if (!cupSizeSelected) {
            sweetCream.setDisable(false);
            frenchVanilla.setDisable(false);
            irishCream.setDisable(false);
            caramel.setDisable(false);
            mocha.setDisable(false);
            quantitySelection.setDisable(false);

            quantitySelection.setValue("1");
            quantityConfirmationText.setText("Quantity: 1");

            cupSizeSelected = true;
        }


        String selected = cupSizeSelector.getSelectionModel().getSelectedItem();
        cupSizeConfirmationText.setText(selected + " selected.");
        cupSize = cupSizeSelector.getValue();

        calculateAndSetSubtotal();
    }

    /**
     * Event handler for the add-ins checkboxes. A confirmation text will tell the user if an add-in is added or
     * removed. The add-ins will be stored to be sent later to the basket. Every time an add-in is added or removed
     * the price will change accordingly.
     * @param event: the action taken by the user.
     */
    @FXML
    protected void addInsSelected(ActionEvent event) {
        CheckBox checkBox = (CheckBox) event.getSource();
        if (checkBox.isSelected()) {
            addInsConfirmationText.setText(checkBox.getText() + " is added.");
            addIns.add(checkBox.getText());
        } else {
            addInsConfirmationText.setText(checkBox.getText() + " is removed.");
            addIns.remove(checkBox.getText());
        }

        if (cupSizeSelector.getValue() != null)
            calculateAndSetSubtotal();
    }

    /**
     * Event handler for the quantity drop down menu. Quantity is defaulted to one when a cup size is selected.
     * A confirmation text will be displayed based on the quantity a user chooses. The subtotal will be updated
     * accordingly based on the quantity selected.
     * @param event: the action taken by the user.
     */
    @FXML
    protected void quantitySelected(ActionEvent event) {
        if (cupSizeSelected) {
            String selected = quantitySelection.getSelectionModel().getSelectedItem();
            quantityConfirmationText.setText("Quantity: " + selected);

            calculateAndSetSubtotal();
        }
    }

    /**
     * Method that calculates the subtotal of the coffee order created.
     */
    private void calculateAndSetSubtotal() {
        coffeeOrder.getChildren().clear();

        Coffee coffee = new Coffee(cupSize, addIns);
        coffeeOrder.getChildren().add(new Text(coffee.toString()));

        int quantity = Integer.parseInt(quantitySelection.getValue());
        subtotal =  quantity * coffee.itemPrice();
        subtotalLabel.setText("Subtotal:\t$" + String.format("%.2f", subtotal));
    }

    /**
     * Method that adds the created coffee order to the basket. A confirmation text is displayed if the coffee is
     * successfully added to the basket, otherwise if a cup size is not selected then it will not allow the user
     * to add the coffee.
     */
    @FXML
    protected void onAddToBasketClick() {
        if (cupSizeSelected) {
            addToBasketConfirmation.setText("Order added to basket.");
            mainController.resetCoffee();
            mainController.getCoffee().setCupSize(cupSize);
            mainController.getCoffee().setAddIns(addIns);
//            System.out.print(addIns);
            mainController.getOrder().addItemToOrder(mainController.getCoffee());
            System.out.print(mainController.getOrder());
        }
        else
            addToBasketConfirmation.setText("Please select a cup size.");
    }
}