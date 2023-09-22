package dude.command;

import dude.Dude;
import dude.Storage;
import dude.exception.DudeException;
import dude.task.TaskList;

/**
 * Command to sort all tasks by a given parameter and print the list.
 */
public class SortTasksCommand extends DudeCommand {
    private SortByType sortByType;
    private SortByOrder sortByOrder;

    public SortTasksCommand(SortByType sortByType, SortByOrder sortByOrder) {
        this.sortByType = sortByType;
        this.sortByOrder = sortByOrder;
    }

    /**
     * Sorts tasks and prints sorted task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DudeException {
        taskList.sort(sortByType, sortByOrder);
        storage.save(taskList.toArrayList());
        return taskList.toString();
    }

    /**
     * {@inheritDoc}
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
