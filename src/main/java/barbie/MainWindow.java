package barbie;


import barbie.types.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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
    @FXML
    private Button viewButton;

    private Barbie barbie;



    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Mochi.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Barbie.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the barbie bot.
     * @param b barbie instance
     */
    public void setBarbie(Barbie b) {
        barbie = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Barbie's reply.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = barbie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (barbie.getDoExit()) {
            Platform.exit();
        }
    }

}
