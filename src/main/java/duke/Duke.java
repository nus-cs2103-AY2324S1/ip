package duke;

import duke.command.Command;

/**
 * The main class of the Duke application, responsible for handling user interactions
 * and managing the core functionality of the task management system.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object. Initializes the user interface, storage, and task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data", "duke.txt");

        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showErrMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initiates the main loop of the Duke application, where user commands are processed.
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
            } catch (DukeException e) {
                ui.showErrMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }
}
