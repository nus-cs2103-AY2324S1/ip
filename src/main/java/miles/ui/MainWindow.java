package miles.ui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import miles.Miles;

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

    private Miles miles;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gwen.png"));
    private Image milesImage = new Image(this.getClass().getResourceAsStream("/images/miles.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Miles object for the MainWindow.
     * @param m the Miles instance
     */
    public void setMiles(Miles m) {
        miles = m;
        dialogContainer.getChildren().addAll(
                DialogBox.getMilesDialog(miles.getGreeting(), milesImage)
        );
    }

    /**
     * Sets the exit for the MainWindow.
     */
    public void setExit() {
        int delayInMillis = 1500;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, delayInMillis);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = miles.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMilesDialog(response, milesImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            setExit();
        }
    }
}
