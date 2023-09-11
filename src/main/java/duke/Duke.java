package duke;

import java.util.ArrayList;

/**
 * The main class for the Duke application.
 */
public class Duke {
    protected static Storage storage = new Storage();
    protected static Ui ui = new Ui();
    protected static ArrayList<Task> taskList = new TaskList();
    protected static int index = 0;

    public String getResponse(String input) throws DukeException {
        index = taskList.size();
        return Parser.parse(taskList, index, storage, input);
    }
}
