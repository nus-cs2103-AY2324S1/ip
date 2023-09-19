package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final Path dukeFilePath = Paths.get("./data/duke.txt");
    private Duke duke;
    private TaskList tasks;
    private Storage storage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Displays welcome message
     */
    public void showWelcomeMessage() {
        Label dukeText = new Label();
        try {
            dukeText.setText(Ui.printFileContents(dukeFilePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (onlyContainsIntroMessage(dukeText.getText())) {
            dukeText.setText("Hi, I am Zac!\nYou do not have any tasks\nHow can I help you today?\n"
                    + "Type 'help' to see the list of things you can do\n");
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
    }
    private static boolean onlyContainsIntroMessage(String text) {
        String[] lines = text.split("\n");
        return lines.length <= 3;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Used in MainWindow.fxml file.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            // so that user cannot send any more inputs to application
        }
        String response = Parser.parse(input, tasks, storage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), new ImageView(userImage)),
                DialogBox.getDukeDialog(new Label(response), new ImageView(dukeImage))
        );
        userInput.clear();
    }
}
