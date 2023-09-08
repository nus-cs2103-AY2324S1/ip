package duke;

import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

/**
 * Main class to run BUTTER.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Class constructor for Duke.
     * Initialises the ui, storage and tasks used in for the BUTTER chatbot program.
     *
     * @param filePath the path of the file containing results from previous interactions.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), storage, ui);
        parser = new Parser(storage, tasks, ui);
    }

    public String getResponse(String input) {
        return parser.parseInput(input);
    }
}
