package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The main class for the Duke application, a simple task management system.
 * Duke allows users to manage their tasks through a command-line interface.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Response response;

    /**
     * Constructs a new Duke instance.
     *
     * @throws DukeException If there is an issue initializing Duke or loading tasks from the data file.
     */
    public Duke() {
        try {
            this.response = new Response();
            this.storage = new Storage("data/duke.txt");
            this.tasks = storage.loadIntoList(new TaskList());
        } catch (DukeException e) {
            response.printException(e.getMessage());
        }
        assert response != null : "Response should not be null";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "Tasks should not be null";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, response, storage);
        } catch (DukeException e) {
            return response.printException(e.getMessage());
        }
    }

}
