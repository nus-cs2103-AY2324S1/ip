package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an FindCommand where the user is finding tasks based on a target string.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public String target;

    /**
     * Public constructor for FindCommand
     *
     * @param target the targeted string that the user is searching for
     */
    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        List<Task> res = new ArrayList<>();
        for (Task task: tasks.getAll()) {
            if (task.getDescription().contains(this.target)) {
                res.add(task);
            }
        }

        if (res.size() > 0) {
            ui.showTasks(res, true);
        } else {
            ui.showNoTasks();
        }
    }
}
