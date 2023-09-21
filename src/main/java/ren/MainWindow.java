package ren;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

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

    private Ren ren;
    private Stage primaryStage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Ren.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Jing.jpg"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRen(Ren r) {
        ren = r;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private void closeAndExit() {
        // Close the window
        primaryStage.close();

        // Exit the program
        Platform.exit();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ren.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        if (input.equals(Commands.EXIT_COMMAND.getValue())) {
            // spawn separate thread to wait 1 second to close the window and exit the program
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Platform.exit();
                }
            }, 1000);
        }
    }
}
