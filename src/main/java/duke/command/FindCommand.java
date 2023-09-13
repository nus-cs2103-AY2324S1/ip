package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import duke.task.Task;


/**
 * The FindCommand is a class representing a command to find tasks containing the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object with the keyword to search for.
     *
     * @param keyword The keyword associated with the task to be searched.
     */
    public FindCommand(String keyword) {
        super(null);
        this.keyword = keyword;
    }

    /**
     * Executes the find command searching and displaying tasks related to keyword.
     *
     * @param tasks The task list to be interacted with.
     * @param ui The user interface for displaying messages.
     * @param storage The storage to save or update tasks to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatch(tasks.filterTasks(task -> task.contains(keyword)));
    }
}
