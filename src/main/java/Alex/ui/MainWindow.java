package Alex.ui;

import Alex.Alex;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Alex alex;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image alexImage = new Image(this.getClass().getResourceAsStream("/images/DaAlex.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAlex(Alex a) {
        this.alex = a;
        // greeting
        String greeting = Ui.greet();
        String helpForAddCommand = Ui.helpForAddCommand();
        String helpForEditCommand = Ui.helpForEditCommand();
        String helpForViewCommand = Ui.helpForViewCommand();
        String helpForExitCommand = Ui.helpForExitCommand();
        dialogContainer.getChildren().addAll(
                DialogBox.getAlexDialog(greeting, alexImage),
                DialogBox.getAlexDialog(helpForAddCommand, alexImage),
                DialogBox.getAlexDialog(helpForEditCommand, alexImage),
                DialogBox.getAlexDialog(helpForViewCommand, alexImage),
                DialogBox.getAlexDialog(helpForExitCommand, alexImage)
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alex's reply and then append
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = alex.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAlexDialog(response, alexImage)
        );
        userInput.clear();
    }
}
