package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * Represents the Duke Class.
 *
 * @author Shishir
 */
public class Duke {

    /** Task List of all tasks. */
    private TaskList tasks;

    /** Storage of all tasks. */
    private Storage storage;

    /** UI for chatting and interacting with the bot. */
    private Ui ui;

    /**
     * Constructs the Duke Object.
     * @param filePath Path of the text file with stored tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Constructs the Duke Object with the default file path
     * being data/tasks.txt.
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.tasks = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Initialises Jarvis
     * @param args Input args.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException exc) {
            return exc.getMessage();
        }
    }

}
