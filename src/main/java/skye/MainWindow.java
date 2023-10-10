package skye;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import skye.ui.DialogBox;
import skye.ui.UI;

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

    private Skye skye;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image skyeImage = new Image(this.getClass().getResourceAsStream("/images/DaSkye.png"));

    /**
     * Performs the initialization of the layout in the JavaFX application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Set the bottom anchor of scrollPane to be scrollPane height - userInput height
        AnchorPane.setBottomAnchor(scrollPane, userInput.getPrefHeight());
        // Set the right anchor of userInput to be userInput width - sendButton width
        AnchorPane.setRightAnchor(userInput, sendButton.getPrefWidth());
        // Set spacing of 10 between children of dialogContainer
        dialogContainer.setSpacing(10);
        // Send welcome message
        dialogContainer.getChildren().addAll(
                DialogBox.getSkyeDialog(UI.showWelcome(), skyeImage)
        );
    }

    public void setSkye(Skye s) {
        skye = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = skye.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getSkyeDialog(response, skyeImage)
            );
            userInput.clear();
        }

        if (skye.isExit()) {
            // Exit from the program after a delay of 1 second.
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 1000);
        }
    }
}
