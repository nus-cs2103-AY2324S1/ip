package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Duke is a task management application that allows users to manage tasks
 * by adding, marking as done, deleting, and listing tasks.
 */
public class Duke {
    private final String name;
    private final TaskList taskList;

    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param name     The name of the Duke application.
     * @param filePath The path to the file used for storing tasks.
     */
    public Duke(String name, String filePath) {
        this.name = name;
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        storage.loadTasks(this.taskList, ui);
    }

    /**
     * Runs the Duke application, handling user input and commands.
     */
    public String getResponse(String input) {
        this.ui.clearOutput();
        assert this.ui.getOutput().equals("") : "Output should be empty before processing input";
        Command command = Parser.processInputIntoCommand(input, this.ui);
        if (command != null) {
            command.execute(this.taskList, this.storage, this.ui);
        }
        return this.ui.getOutput();
    }

    /**
     * Returns the name of the Duke application.
     *
     * @return The name of the Duke application.
     */
    public String getName() {
        return this.name;
    }
}
