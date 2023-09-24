package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.text.Text;

import main.UI;

import main.Duke;

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
    private Text dialogText;
    @FXML
    private Button sendButton;

    private Duke duke;

    private UI ui = new UI();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = ui.printWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
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
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        userInput.clear();

//        dialogContainer.getChildren().add(
//                DialogBox.getUserDialog(input, userImage)
//        );
//
//        // Create a timeline with a delay of 1 second
//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.seconds(0.3),
//                event -> dialogContainer.getChildren().add(
//                        DialogBox.getDukeDialog(response, dukeImage)
//                ))
//        );
//
//        // Play the timeline to introduce the delay
//        timeline.play();

//        userInput.clear();
    }
}

