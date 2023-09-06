package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duke.Duke;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke rion;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image rionChatBot = new Image(this.getClass().getResourceAsStream("/images/Rion.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRion(Duke rion) {
        this.rion = rion;
        dialogContainer.getChildren().addAll(
            DialogBox.getRionDialog(rion.getGreeting(), rionChatBot)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = rion.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, user),
            DialogBox.getRionDialog(response, rionChatBot)
        );
        userInput.clear();
    }
}