package duke;

import commands.Command;
import exceptions.JamesBondException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


/**
 * The `Duke` class is the main class for the Duke task management application.
 * It handles the initialization of various components and the execution of the application.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a `Duke` object with the specified file path for data storage.
     *
     * @param filePath The file path where task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            TaskList tasks = new TaskList();
            tasks = storage.loadTasksFromFile();
            this.tasks = tasks;
        } catch (JamesBondException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }


    /**
     * Runs the Duke application.
     * It displays a welcome message and continuously reads user input, parsing and executing commands
     * until there is no more input.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (ui.isThereNext()) {
            String input = ui.readLine();
            Command c = Parser.parseCommand(input);
            c.runCommand(tasks, ui, storage);
        }
    }

    /**
     * The main method to start the Duke application.
     * It creates an instance of `Duke` and runs the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("/Users/jamesbond/ip/data/jamesbond.txt").run();
    }
}
