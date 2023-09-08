package evo;

import java.util.Objects;
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
    private Evo evo;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/leaf.png"));
    private Image evoImage = new Image(this.getClass().getResourceAsStream("/images/milkglass.png"));

    /**
     * Initializes the MainWindow, binding the scrollPane to the dialogContainer height property.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of the Evo application to be used by the MainWindow.
     *
     * @param e The Evo application instance.
     */
    public void setEvo(Evo e) {
        evo = e;
    }

    /**
     * Displays a welcome message from Evo to the user.
     */
    public void welcomeUser() {
        dialogContainer.getChildren().add(DialogBox.getEvoDialog(evo.showWelcomeMessage(), evoImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Evo's reply and then appends them to
     * the dialog container. Clears the user input after processing. If the input is "bye", then quit the program.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = evo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEvoDialog(response, evoImage)
        );
        userInput.clear();

        if (Objects.equals(input, "bye")) {
            javafx.application.Platform.exit();
        }
    }
}
