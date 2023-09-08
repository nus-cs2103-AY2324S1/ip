package joe;

import java.io.IOException;

import javafx.application.Application;
import joe.commands.Command;
import joe.exceptions.JoeException;
import joe.ui.Ui;

/**
 * Represents the Joe chatbot.
 */
public class Joe {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Joe object with the specified file path for task storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Joe(String filePath) {
        storage = new Storage(filePath);

        try {
            tasks = storage.readTasks();
        } catch (IOException | JoeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Joe object with the default file path for task storage.
     */
    public Joe() {
        storage = new Storage("joe.txt");

        try {
            tasks = storage.readTasks();
        } catch (IOException | JoeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Executes the users command and returns a String result.
     *
     * @param input The user's input into the dialogBox
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (JoeException e) {
            return e.getMessage();
        }
    }

    /**
     * The main entry point of the Joe application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
