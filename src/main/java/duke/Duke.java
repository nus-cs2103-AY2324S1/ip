package duke;

import duke.management.Parser;
import duke.management.Storage;
import duke.management.TaskList;

import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.parser = new Parser();
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert !input.isEmpty() : "Input cannot be empty!";
        String result = this.parser.parse(input, tasks);
        return result;
    }
}