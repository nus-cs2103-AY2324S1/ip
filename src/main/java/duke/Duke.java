package duke;

import duke.commands.Command;

import java.util.Scanner;

/**
 * The Duke class represents a simple task management application.
 * It initializes necessary components and controls the main application flow.
 */
public class Duke {
    private final TaskList list;

    /**
     * Constructs a Duke object with the specified file path for task storage.*
     */
    public Duke() {
        this.list = new TaskList("./data/duke.txt");
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.list);
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
