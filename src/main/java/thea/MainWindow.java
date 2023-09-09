package thea;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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

    private Thea thea;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image theaImage = new Image(this.getClass().getResourceAsStream("/images/Thea.png"));

    /**
     * Initializes MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    public void setThea(Thea t) {
        thea = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Thea's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(thea.tasks, thea.ui, thea.storage);
        } catch (EmptyDescriptionException | WrongCommandException
                 | WrongDateTimeFormatException e) {
            response = e.getMessage();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTheaDialog(response, theaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Timer timer = new Timer();
            int fiveSeconds = 5 * 1000;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    javafx.application.Platform.exit();
                }
            }, fiveSeconds);
        }
    }
    private void greet() {
        dialogContainer.getChildren().add(
                DialogBox.getTheaDialog(Ui.greet(), theaImage)
        );
    }

}
