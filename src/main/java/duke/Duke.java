package duke;

import duke.commands.Command;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.StorageFileException;
import duke.parsers.Parser;
import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The main Duke class to run
 */
public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;

    /**
     * The constructor that takes in a String filePath that specifies the path for the storage file.
     *
     * @param filePath Specifies the path for the storage file
     */
    public Duke(String filePath) {
        // Initialize the task list
        this.list = new TaskList();
        // Initialize the user interface
        this.ui = new Ui();
        // Initialize the storage object
        this.storage = new Storage(filePath);


        try {
            // Attempt to load tasks from storage
            this.list = this.storage.load();
        } catch ( e) {

        } catch ( e) {

        }
    }

    public String getResponse(String input) {
        try {
            this.ui.reset();

            Command command = Parser.parse(input);

            command.execute(this.list, this.ui, this.storage);
i
            return this.ui.getResponse();
        } catch ( e) {
            // append the error message to the ui's response
            this.ui.appendResponse(e.getMessage());
            return this.ui.getResponse();
        }
    }
}

