package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

    //private Duke duke;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser executor;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gitar.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bocchi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        this.executor = new Parser();
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
//  old duke is no longer used
//    public void setDuke(Duke d) {
//        duke = d;
//        this.duke = new Duke();
//    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        try {
            new FileWriter("./data/duke.txt", false).close();
            this.storage.writeToFile(this.tasks);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        userInput.clear();
    }

    private String getResponse(String input) {
        return executor.parse(this.tasks, input);
    }
}
