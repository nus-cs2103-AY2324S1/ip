package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;


/**
 * Uses a keyword to find tasks that contain keyword in description.
 */
public class FindCommand extends Command {

    private final String keyword; //keyword to search for

    /**
     * Constructor for a FindCommand.
     * @param keyword keyword to search the tasklist for
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the instance of FindCommand
     * @param tasklist list of tasks
     * @param ui instance of ui class
     * @param storage instance of storage class
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showTasks(tasklist.filterToList(this.keyword));
    }

    /**
     * Checks if the current task is an exit task.
     * @return false since task is not an exit task
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
