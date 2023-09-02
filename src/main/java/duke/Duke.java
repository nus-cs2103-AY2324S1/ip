package duke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.command.Command;
import javafx.application.Platform;

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
     * Returns the welcome string.
     *
     * @return The string to welcome users.
     */
    public String init() {
        ui.showWelcome();
        return (ui.getCurrentMessage());
    }

    /**
     * Returns the response given the user input.
     *
     * @param input The user input entered.
     * @return The string Bot replies.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> Platform.exit());
            }
        } catch (DukeException e) {
            ui.showErrMessage(e);
        }
        return ui.getCurrentMessage();
    }
}
