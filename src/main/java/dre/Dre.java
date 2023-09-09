package dre;

import dre.exception.DreException;
import dre.ui.Ui;
import dre.storage.Storage;
import dre.parser.Parser;
import dre.task.TaskList;
import dre.command.Command;

/**
 * Represents the main application class, serving as the primary entry point
 * to initiate and run the Dre task management system.
 */
public class Dre {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new instance of Dre, initializing storage, tasks, and user interface components.
     *
     * @param filePath The file path where tasks will be loaded from and saved to.
     */
    public Dre(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
            System.out.println(e);
        }
    }

    /**
     * Runs the main loop of the application, repeatedly reading user commands,
     * parsing them, and executing the corresponding actions until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DreException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method serves as the application's entry point.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Dre("data/dre.txt").run();
    }
}