package duke;

import command.Command;

import dukeutilities.Parser;

import exceptions.DukeException;

import java.time.format.DateTimeParseException;

/**
 * The Duke class initializes the Duke application and manages its execution.
 * It handles user commands, interacts with the file 'duke.txt', and maintains the task list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor initializes all required fields such as Ui, Storage and TaskList
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage("duke.txt", "data");
        tasks = new TaskList();
    }

    /**
     * Runs the Duke application, handling user input and executing commands.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("Incorrect format input");
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the Duke application.
     * Creates a new instance of Duke and starts the application.
     *
     * @param args Command-line arguments (unused in this context).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}






