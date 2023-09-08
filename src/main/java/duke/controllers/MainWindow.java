package duke.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;

import duke.Duke;
import duke.DialogBox;
import duke.ui.Ui;
import javafx.scene.paint.Color;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lebron.png"));

    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());



    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getInitialMessage(), dukeImage));
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }

    private void initiateDelayedExit() {
        Task<Void> exitTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(3000); // Sleep for 3 seconds
                return null;
            }
        };

        exitTask.setOnSucceeded(event -> {
            Platform.exit(); // Exit the JavaFX application
        });

        // Start the task
        new Thread(exitTask).start();
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.startsWith("Bye")) {
            initiateDelayedExit();
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }

}

