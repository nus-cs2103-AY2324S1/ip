package bob;

import bob.data.command.Command;
import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;
import bob.storage.Storage;

/**
 * Represents the main chatbot logic.
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList list;

    /**
     * Initialise a new Duke object with its class fields.
     */
    public void init() {
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
            Command command = parser.parse(input, false);
            response = command.execute(list);
        } catch (DukeException e) {
            return e.toString();
        }
        this.end();
        assert response != null : "response should not be null";
        return response;
    }
}
