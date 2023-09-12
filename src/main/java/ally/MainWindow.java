package ally;

import ally.commands.Commands;
import ally.exceptions.AllyException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import ally.Ui;
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

    private Ally ally;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String startMessage = "Hello I'm ALLY! What can I do for you?"
                + "\n" + "Reminder to type deadline task in this format [deadline] [task] /by yyyy-MM-dd HHmm";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(startMessage, dukeImage)
        );
    }

    public void setDuke(Ally a) {
        ally = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws AllyException {
//        String input = userInput.getText();
//        String response = ally.getResponse(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(input, userImage),
//                DialogBox.getDukeDialog(response, dukeImage)
//        );
//        userInput.clear();
//        if (input.equals("bye")) {
//            Platform.exit();
//        }
        String input = userInput.getText();
        if (input.equalsIgnoreCase("bye")) {
            // Append a "bye" message to the conversation
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Goodbye! Have a nice day.", dukeImage)
            );
            userInput.clear();

            // Create a Timeline to introduce a delay
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> {
                        Platform.exit(); // Exit the application after a 2-second delay
                    })
            );
            timeline.setCycleCount(1); // Run the timeline once
            timeline.play();
        } else {
            // Process other user input
            try {
                String response = ally.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, dukeImage)
                );
                userInput.clear();
            } catch (AllyException e) {
                // Handle the exception by displaying an error dialog
                Platform.runLater(() -> showErrorDialog("Error", e.getMessage()));
            }
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


