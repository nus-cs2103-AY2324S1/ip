package anya;

import java.io.IOException;

import anya.parser.Parser;
import anya.storage.Storage;
import anya.storage.Storage.InvalidStorageFilePathException;
import anya.storage.Storage.StorageOperationException;
import anya.task.TaskList;
import anya.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents a chat bot named anya.
 */
public class Anya {
    private static final String STORAGE_FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/damian.png"));
    private Image anya = new Image(this.getClass().getResourceAsStream("/images/anya.png"));
    /**
     * Constructs a new instance of the {@code Anya} class with the specified storage file path.
     * This constructor initializes the user interface (UI), storage, and task list parser for the Anya application.
     * It also loads tasks from the specified storage file and handles exceptions related to storage operations.
     *
     * @throws RuntimeException If there is an issue with the storage file path or storage operation,
     *                          a runtime exception is thrown, and an initialization error message is
     *                          displayed through the UI.
     */
    public Anya() {
        try {
            this.ui = new Ui();
            this.storage = new Storage(STORAGE_FILE_PATH);
            tasks = storage.load();
            this.parser = new Parser(storage, tasks);
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showInitError();
            throw new RuntimeException(e);
        }
    }
    /**
     * Executes the main functionality of the Anya application by running its core components.
     * This method is responsible for executing the main workflow of the Anya application, which includes displaying a
     * greeting message, parsing user input, and showing an exit message when the application is finished.
     *
     * @throws IOException If an I/O error occurs during the execution of this method, it may throw an IOException.
     *
     * @see Ui
     * @see Parser
     */
    public void run() throws IOException {
        ui.showGreetingMessage();
        parser.parse();
        ui.showExitMessage();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return input;
    }
}
