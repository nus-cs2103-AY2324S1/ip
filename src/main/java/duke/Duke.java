package duke;

import java.nio.file.Path;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.CliUi;

/**
 * Duke is a chatbot that helps you keep track of your tasks.
 * In this version, Duke is renamed to LilBro.
 */
public class Duke {

    /** The task list that is used to store the user's tasks. */
    private final TaskList taskList = new TaskList();

    /** The storage that is used to save and load the user's tasks. */
    private final Storage storage;

    private final Parser parser = new Parser(this.taskList);

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Constructs a new Duke object.
     *
     * @param path The path to the save file.
     */
    public Duke(String path) {
        CliUi.println("Checking for a save file...");
        String projectRoot = System.getProperty("user.dir");
        String fullPath = Path.of(projectRoot, path).toString();
        this.storage = new Storage(fullPath);
        try {
            storage.load(this.taskList);
        } catch (DukeException e) {
            CliUi.println(e.getMessage());
        }
    }

    /**
     * The main method is used to run the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    public String greet() {
        return "Hello! I'm LilBro!\nWhat can I do for you?";
    }

    /**
     * Stores current task list into local storage.
     */
    public void exit() {
        CliUi.println("Before you go, let me save your tasks...");
        storage.save(this.taskList);
    }

    public Command parseInput(String input) {
        return this.parser.parse(input);
    }
}
