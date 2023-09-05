package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.list.FunnyList;

/**
 * Main class for the Duke application.
 * This class initializes and manages the Duke application. It handles user interaction,
 * task processing, and exception handling.
 */
public class Duke extends Application {
    private Storage storage;
    private FunnyList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./tasks.txt");
        try {
            this.tasks = new FunnyList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new FunnyList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Start the Duke application.
     *
     * @param args Command-line arguments (Not applicable).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the main loop of the Duke application.
     * Processes user commands and handles exceptions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.printLine();
            }
        }
    }
}
