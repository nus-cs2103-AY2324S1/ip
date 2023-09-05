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
        storage.loadTasks(this.taskList);
    }

    /**
     * The main method that initializes and runs the Duke application.
     *
     * @param args Command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        Duke duke = new Duke("Duke", "src/main/java/resource/duke.txt");
        duke.run();
    }

    /**
     * Runs the Duke application, handling user input and commands.
     */
    public void run() {
        ui.greet(this.name);
        String input = ui.readCommand();
        while (!input.equals("bye")) {
            Command command = Parser.handleInput(input, this.ui);
            if (command != null) {
                command.execute(this.taskList, this.storage, this.ui);
            }
            input = ui.readCommand();
        }
        ui.exit();
    }
}
