package duke.controller;

import duke.Duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindowController {

    @FXML
    private AnchorPane mainLayout;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Stage primaryStage;

    @FXML
    private void initialize() {
        userInput.setOnAction(event -> handleUserInput());
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setUserInput(String text) {
        this.userInput.setText(text);
    }

    @FXML
    private void handleSendButtonAction() {
        handleUserInput();
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String response = Duke.processInput(userInput.getText());

        if ("SHUTDOWN".equals(response)) {
            Label byeMessage = new Label("Bye. Hope to see you again soon!");
            dialogContainer.getChildren().add(DialogBoxController.getDukeDialog(byeMessage));

            // Introduce a delay of 2 seconds (or any duration you prefer)
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> primaryStage.close());
            delay.play();
            return;
        }

        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(DialogBoxController.getUserDialog(userText), DialogBox.getDukeDialog(dukeText));
        userInput.clear();
    }
}
