package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Find command to be executed.
 */
public class FindCommand extends Command {
    private String queryString;

    public FindCommand(String queryString) {
        super(false);
        this.queryString = queryString;
    }

    /**
     * Executes the list of commands to find a Task from a TaskList.
     *
     * @param taskList The given TaskList to be searched on.
     * @param ui The given Ui to show the status of the finding of the task.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTaskList = taskList.find(this.queryString);
        ui.addFindMessage();
        ui.addTaskList(filteredTaskList);
    }
}