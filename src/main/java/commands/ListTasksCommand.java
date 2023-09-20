package commands;
import oop.Storage;
import oop.TaskList;
import oop.Ui;
import tasks.Task;

/**
 * The command that lists all the currently stored tasks by wallE.
 */
public class ListTasksCommand implements Command {
    /**
     * {@inheritDoc}
     * Executes the command to list out all the tasks that are currently stored in the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            return "You don't have any tasks yet! Use the `help` command to find out how you can add tasks!";
        }
        String res = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            res += ui.getTaskString(i, task);
        }
        return res;
    }
}
