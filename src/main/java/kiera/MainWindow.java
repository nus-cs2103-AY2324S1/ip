package kiera;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import kiera.command.Command;
import kiera.exception.KieraException;

/**
 * Main window containing JavaFX components for the user interface and managing interactions.
 */
public class MainWindow {

    protected static String filePath = "./data/storage.txt";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKiera() {
        try {
            ui = new Ui(dialogContainer);
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (KieraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            ui.showUserCommand(input);
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (KieraException e) {
            ui.showError(e.toString());

        } finally {
            userInput.clear();
        }
    }
}
