package duke;

import duke.management.Parser;
import duke.management.Storage;
import duke.management.TaskList;

import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    final static String LINE = "────────────────────────────────────────────────────";

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Contains the logic flow of the Duke program.
     */
    public void run() {
        try {
            try {
                storage.writeTasksToFile(tasks.getTasks());
            } catch (IOException e) {
                throw new DukeException("Cannot write tasks into file!");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n" + LINE);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        String result = parser.parse(input, tasks);
        return result;
    }
}