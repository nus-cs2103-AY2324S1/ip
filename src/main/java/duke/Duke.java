package duke;

import duke.command.Command;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * The {@code Duke} class is the main class for the Duke task manager application.
 * It initializes and coordinates the application's components, such as the UI, parser, storage, and task list.
 * The class also defines the main method for launching the application and the main loop for user interaction.
 */
public class Duke {

    public static final String PATH = "src/main/data/task-list.txt";
    private TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    /**
     * Initializes the Duke application by creating instances of the UI, parser, and storage.
     */
    public Duke() {
        this.parser = new Parser();
        this.storage = new Storage(parser);
        this.tasks = new TaskList(storage.loadTasksFromFile());

        // Add assertions to check the initial state of Duke's components
        assert parser != null : "Parser should not be null";
        assert storage != null : "Storage should not be null";
        assert tasks != null : "TaskList should not be null";
    }

    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return farewell();
            } else {
                Command command = parser.parseInput(input);
                String response = command.execute(tasks);
                storage.saveTasksToFile(tasks);
                return response;
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        return "";
    }

    private String farewell() {
        return "Bye. Hope to see you again soon!";
    }
}