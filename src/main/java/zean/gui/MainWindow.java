package zean.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zean.Zean;

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
    private Zean zean;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the main window with the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getZeanDialog("Hi there, I'm Zean!\nHow can I help you today?", duke)
        );
    }

    public void setZean(Zean z) {
        zean = z;
    }

    /**
     * Creates a dialog box, containing message from Zean and appending it to the dialog container.
     *
     * @param msg The message to be displayed.
     */
    public void showMessage(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getZeanDialog(msg, duke)
        );
    }

    /**
     * Creates an error dialog box, containing the error message and appending it to the dialog container.
     *
     * @param msg The message to be displayed.
     */
    public void showErrorMessage(String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getErrorDialog(msg, duke)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zean's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox zeanResponseDialog = zean.getResponseDialog(input, duke);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                zeanResponseDialog
        );
        userInput.clear();
    }


}
