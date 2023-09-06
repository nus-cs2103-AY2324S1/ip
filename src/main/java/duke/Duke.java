package duke;

import duke.dataFile.Storage;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.TaskList;

public class Duke {
    private final Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return Parser.parseCommands(input, this.tasks, this.storage);
    }
}
