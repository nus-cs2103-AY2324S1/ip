package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find and display tasks in Duke's task list that match a specified keyword.
 */
public class FindCommand extends Command {

    private String target;

    /**
     * Constructs a FindCommand with the specified keyword to search for.
     *
     * @param target The keyword to search for in task descriptions.
     */
    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFilteredTasks(taskList.find(this.target));
    }
}
