package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import command.Command;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * The `Duke` class serves as the main class for the BloopBot application.
 * It handles user interactions, command parsing, and task management.
 * This class initializes and manages the `TaskList`, `Ui`, and `Storage` components.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Duke {
    private Scanner scanner;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new `Duke` instance and initializes its components.
     * It creates a scanner for user input, initializes the task list, UI, and storage components,
     * and loads tasks from a data file if available.
     *
     * @throws DukeException     If an error occurs during initialization.
     * @throws FileNotFoundException If the task data file is not found.
     */
    public Duke() throws DukeException, FileNotFoundException {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("data", "tasks.txt", taskList);
        storage.loadTasks();
    }

    /**
     * Runs the BloopBot application, displaying a welcome message and handling user input.
     * It continues to read user commands, parse and execute them until the exit command is given.
     *
     * @throws DukeException If an error occurs during command execution.
     */
    public void run() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the BloopBot application.
     * It creates an instance of `Duke` and runs the application.
     *
     * @param args The command-line arguments (not used).
     * @throws DukeException     If an error occurs during application execution.
     * @throws FileNotFoundException If the task data file is not found.
     */
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        try {
            Duke bloopBot = new Duke();
            bloopBot.run();
        } catch (DukeException | FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
