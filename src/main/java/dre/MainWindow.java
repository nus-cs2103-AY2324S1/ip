package dre;

import dre.gui.DialogBox;
import dre.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;



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

    private Dre dre;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dreImage = new Image(this.getClass().getResourceAsStream("/images/DaDre.jpg"));

    /**
     * Initializes the MainWindow with the appropriate configurations.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDreDialog(Ui.showWelcome(), dreImage)
        );
    }

    /**
     * Sets the instance of Dre to be used by this window.
     *
     * @param d The instance of Dre to be set.
     */
    public void setDre(Dre d) {
        dre = d;
    }

    /**
     * Handles user input by creating two dialog boxes: one to echo the user input and the other
     * to show Dre's reply. These are appended to the dialog container.
     * The user input is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dre.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDreDialog(response, dreImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            closeWindow();
        }
    }

    /**
     * Closes the application window after a short delay.
     */
    @FXML
    private void closeWindow() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> javafx.application.Platform.exit());
        delay.play();
    }
}
