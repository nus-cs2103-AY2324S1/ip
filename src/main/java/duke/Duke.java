package duke;

import commands.Command;
import exceptions.DukeException;
import io.Parser;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Chatbot to interact and manage tasks.
 */
public class Duke {

    /** Storage to save and load from local. */
    private Storage storage;
    /** List of tasks. */
    private TaskList tasks;
    /** User interface */
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println("Your command: " + fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.canExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
