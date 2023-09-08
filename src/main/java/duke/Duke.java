package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A chatbot that helps user to maintain their tasks.
 *
 * @author Joseph Oliver Lim
 */
public class Duke {
    private TaskList tasks = new TaskList();
    private Scanner sc;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs an instance of Duke chatbot.
     */
    public Duke() {
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.storage.initialize();
            this.tasks = this.storage.readFile();
        } catch (DukeException e) {
            this.ui.errorMessage(e);
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public String run(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return this.ui.errorMessage(e);
        }
    }

    /**
     * Gets a response from the Duke chatbot.
     */
    protected String getResponse(String input) {
        return run(input);
    }
}
