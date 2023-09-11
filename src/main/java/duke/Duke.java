package duke;

import duke.command.Command;

import duke.task.TaskList;

/**
 * The Duke class represents a simple task management application.
 * It initializes necessary components and controls the main application flow.
 */
public class Duke {
    private final TaskList tasks;

    /**
     * Constructs a Duke object with the specified file path for task storage.*
     */
    public Duke() {
        this.tasks = new TaskList("./data/duke.txt");
    }

    /**
     * Outputs a response after executing a command based on user input.
     *
     * @param input The user input to process.
     * @return A response message based on the input command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments passed into Launcher.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
