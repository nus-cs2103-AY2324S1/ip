package evo;

import java.io.IOException;

import evo.commands.ByeCommand;
import evo.commands.Command;
import evo.exceptions.EvoException;
import evo.parser.Parser;
import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Evo is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * Tasks are represented by the nested static class Task.
 */
public class Evo extends Application {

    /**
     * The file path where task data is stored.
     */
    private static final String FILE_PATH = "./data/evo.txt";
    /**
     * The tasksList that holds and manages tasks.
     */
    private TaskList tasksList;
    /**
     * The storage component responsible for loading and saving task data.
     */
    private Storage storage;
    /**
     * The user interface component for displaying messages to the user.
     */
    private Ui ui;

    /**
     * Constructs an `Evo` object with the specified file path for task data.
     *
     * @param filePath The file path where task data is stored.
     */
    public Evo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TaskList(storage.loadTasksFromFile());
        } catch (EvoException e) {
            ui.showLoadingError();
            tasksList = new TaskList();
        }
    }

    /**
     * Runs the `Evo` program, displaying a welcome message and processing user commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser p = new Parser(tasksList);
                Command c = p.parse(fullCommand);
                if (c instanceof ByeCommand) {
                    c.execute(tasksList, ui, storage);
                    isExit = true;
                    break;
                }
                if (c == null) {
                    continue;
                }
                c.execute(tasksList, ui, storage);
                storage.saveTasksInFile(tasksList);
            } catch (IOException e) {
                ui.showText("Something went wrong: " + e.getMessage());
            }
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
     * The main method of Evo program.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        new Evo(FILE_PATH).run();
    }
}
