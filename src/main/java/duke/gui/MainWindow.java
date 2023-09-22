package duke.gui;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the Duke chat application.
     * This method is automatically called after loading the FXML file and initializing the UI components.
     * It sets up the initial state of the GUI, binds scrolling to the chat container, sets user input styles,
     * and displays a welcome message from Duke.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.setStyle("-fx-background-color: #333;");

        // Set font and text color for the user input field
        userInput.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");

        // Set a prompt text (placeholder text) for user input field
        userInput.setPromptText("Type your message...");

        // Display a hello message when the GUI starts
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Axela. What can I do for you?\n\nIf unsure of what to do just "
                                + "type in 'help' to see all the command possible", dukeImage,
                            "-fx-background-color: #3498db; -fx-text-fill: black;")
        );
    }


    public void setDuke(Duke d) {
        duke = d;
    }

    public Duke getDuke() {
        return duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().toLowerCase(); // Convert to lowercase
        String response = duke.getResponse(input);

        // Create user and Duke dialogs and add them to the container
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage, "-fx-background-color: #777;"),
                DialogBox.getDukeDialog(response, dukeImage, "-fx-background-color: #3498db;")
        );

        if (input.equalsIgnoreCase("bye")) {
            // Create a Timeline to delay program exit
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> exitApplication()));
            timeline.setCycleCount(1);
            timeline.play();
        }

        userInput.clear();
    }

    private void exitApplication() {
        // Exit the program
        System.exit(0);
    }
}
