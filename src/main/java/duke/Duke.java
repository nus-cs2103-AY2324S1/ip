package duke;

import duke.command.Command;
import duke.exception.DukeDatabaseNotFoundException;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class for the Duke task management application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filePath = "data/tasks.txt";

    /**
     * Constructor for Duke, which instantiates the ui, storage and taskList.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadData());
        } catch (DukeDatabaseNotFoundException e) {
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseUserInput(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }

}
