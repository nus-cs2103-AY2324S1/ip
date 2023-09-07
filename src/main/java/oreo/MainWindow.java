package oreo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import oreo.command.Command;
import oreo.exception.IllegalDateTimeException;
import oreo.parser.Parser;
import oreo.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Oreo oreo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image oreoImage = new Image(this.getClass().getResourceAsStream("/images/oreo.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // scroll follows
    }

    public void setOreo(Oreo oreo) {
        this.oreo = oreo;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command c = Parser.parse(input);
        if (c.isExit()) {
            exit(input);
        }
        String response = oreo.execute(c);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOreoDialog(response, oreoImage)
        );
        userInput.clear();
    }

    @FXML
    public void startUp() {
        try {
            oreo.startUp();
            greetUser();
        } catch (FileNotFoundException | IllegalDateTimeException |
                 InputMismatchException e) {
            oreo.clearTaskAndFile();
            String fileCorruptMessage = "saved file is corrupt, creating new file...";
            dialogContainer.getChildren().addAll(
                    DialogBox.getOreoDialog(fileCorruptMessage, oreoImage));
        }
    }

   @FXML
    private void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getOreoDialog(oreo.greet(), oreoImage));
    }

    @FXML
    private void exit(String input) {
        try {
            oreo.closeProcess();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getOreoDialog(oreo.sayBye(), oreoImage)
            );
            Platform.exit();
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getOreoDialog(e.getMessage(), oreoImage));
        }

    }
}
