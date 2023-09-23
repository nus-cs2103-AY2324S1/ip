package gui;

import bocchi.Bocchi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI window of the Bocchi application. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/larry.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Bocchi bocchi;
    private Stage stage;

    /**
     * Initializes the controller.
     * Binds the vertical scroll position of the scrollPane to the height of the dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets up the Bocchi instance and associates it with the current stage.
     *
     * @param stage The primary stage for the application.
     */
    public void setBocchi(Stage stage) {
        this.bocchi = new Bocchi(dialogContainer);
        this.stage = stage;
    }

    /**
     * Handles user input, displays it in a dialog box, and processes the user's request using the Bocchi instance.
     * Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        boolean isTerminated = bocchi.getResponse(input);
        userInput.clear();
        if (isTerminated) {
            this.stage.close();
        }
    }
}
