package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents command Find that finds for tasks with a specific keyword.
 *
 * @author Armando Jovan Kusuma
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword
     * to be found in the list of tasks.
     *
     * @param keyword The keyword to find in the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.contains(keyword)) {
                foundTasks.add(currTask);
            }
        }
        ui.printFoundTasks(foundTasks);
    }
}
