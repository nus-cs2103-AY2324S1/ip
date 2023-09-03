package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to find tasks based on search string */
public class FindCommand implements Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Finds and prints tasks based on search string.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.print(searchString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
