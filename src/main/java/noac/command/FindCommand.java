package noac.command;

import noac.NoacException;
import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Task;

import java.util.ArrayList;

/**
 * For executing the find command.
 */
public class FindCommand extends Command{

    private String searchString;

    /**
     * Create the OnCommand class.
     *
     * @param searchString String to find matching tasks.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Find all the task that contains that string and display it.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {

        ArrayList<Task> returnTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(this.searchString)) {
                returnTasks.add(tasks.getTask(i));
            }
        }

        ui.showFind(returnTasks);

    }
}
