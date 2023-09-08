package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;

public class ListTasksCommand implements Command {
    /**
     * {@inheritDoc}
     * Executes the command to list out all the tasks that are currently stored in the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            res += ui.printTask(i, task);
        }
        return res;
    }
}
