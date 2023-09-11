package duke;

import java.io.IOException;

import duke.management.Parser;
import duke.management.Storage;
import duke.management.TaskList;

/**
 * Duke program.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;

    /**
     * Duke Constructor.
     *
     * @param filePath Relative file path to the data file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Save all updated data to the data file at the end of Duke.
     */
    public void saveToFile() {
        try {
            this.storage.writeTasksToFile(this.tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Cannot write tasks into file!");
        }
    }

    /**
     * Gets the response of the bot.
     *
     * @param input User's input.
     * @return Returns the bot's response in String representation.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        String result = parser.parse(input, tasks);
        return result;
    }
}
