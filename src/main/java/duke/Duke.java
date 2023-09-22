package duke;

import duke.commands.Command;
import duke.exceptions.FileIoException;
import duke.exceptions.UnknownCommandException;
import duke.io.Storage;
import duke.parsers.CommandParser;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The main Duke class to run.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes a new Duke instance with a given file path for storage.
     *
     * @param filePath The path for the storage file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (UnknownCommandException | FileIoException e) {
            ui.displayMessageWithBars(e.getMessage());
            tasks = new TaskList();
            if (e instanceof FileIoException) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Gets the response from Duke.
     *
     * @param input User input.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        try {
            ui.resetResponse();
            Command command = CommandParser.parse(input);
            command.execute(tasks, ui, storage);
        } catch (Exception e) {
            ui.addToResponse(e.getMessage());
        }
        return ui.getResponse();
    }
}

