package duke.core;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Main class for the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this("./data/");
    }

    /**
     * Constructor for Duke.
     *
     * @param baseDirectory Base directory for the program.
     */
    public Duke(String baseDirectory) {
        try {
            storage = new Storage(baseDirectory);
            tasks = new TaskList(storage.readFile("tasks.txt"));
        } catch (DukeException e) {
            // TODO: Handle error
        }
    }

    /**
     * Returns the response to the user input.
     *
     * @param input User input.
     * @return Response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);

            if (command == null) {
                return "Please enter a valid command.";
            }

            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
