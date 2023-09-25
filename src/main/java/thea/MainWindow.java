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
    }

    public void setThea(Thea t) {
        thea = t;
        loadMessage();
        greet();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Thea's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTheaDialog(response, theaImage)
        );
        userInput.clear();
        exitProgramIfRequested(input);
    }

    private static void exitProgramIfRequested(String input) {
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

    private String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(thea);
        } catch (EmptyDescriptionException | WrongCommandException
                 | WrongDateTimeFormatException e) {
            response = e.getMessage();
        }
        return response;
    }

    private void greet() {
        dialogContainer.getChildren().add(
                DialogBox.getTheaDialog(Ui.greet(), theaImage)
        );
    }

    private void loadMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getTheaDialog(thea.ui.fileLoadedMessage(
                        thea.storage.getFileName()), theaImage)
        );
    }

}
