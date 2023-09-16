package bob;

import bob.data.command.Command;
import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the main chatbot logic.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList list;

    /**
     * Initialise a new Duke object with its class fields.
     */
    public void init() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();
        this.list = new TaskList(this.storage);

        list.open();
    }

    public void end() {
        this.list.close();
    }

    public String getResponse(String input) {
        this.init();
        String response;
        try {
            Command command = parser.parse(input);
            response = command.execute(storage, list, ui);
        } catch (DukeException e) {
            System.out.println(e);
            return "That didn't work as expected.";
        }
        this.end();
        return response;
    }
}
