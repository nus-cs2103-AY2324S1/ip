package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Main;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * Represents a chatbot
 */
public class Duke {
    /**
     * The task list
     */
    private final TaskList taskList;
    /**
     * The ui object
     */
    private final Ui ui;
    /**
     * The storage object
     */
    private final Storage storage;

    /**
     * Constructor for Duke
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.taskList = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Initialises the program
     *
     * @param args Input args.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Gets the response from the bot
     *
     * @param input the input from the user
     * @return the response from the bot
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException exc) {
            return exc.getMessage();
        }
    }

}
