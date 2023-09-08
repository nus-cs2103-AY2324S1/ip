package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Find that finds the tasks that match a certain keyword.
 *
 * @author Joseph Oliver Lim
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with a specified keyword.
     *
     * @param keyword The keyword that is used to find the tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findTasks = new TaskList();
        for (int i = 0; i < tasks.getCountTasks(); i++) {
            Task task = tasks.getTask(i);
            if (task.contains(this.keyword)) {
                findTasks.addTask(task);
            }
        }
        return ui.findTasks(findTasks);
    }
}
