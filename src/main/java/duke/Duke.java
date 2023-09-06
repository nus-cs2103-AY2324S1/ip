package duke;

import duke.dataFile.Storage;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.TaskList;

/**
 * Class for the CringeBot chat bot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath file path to the storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets response from CringeBot, based on the user input.
     *
     * @param input input from the user.
     * @return response from CringeBot.
     */
    public String getResponse(String input) {
        return Parser.parseCommands(input, this.tasks, this.storage);
    }
}
