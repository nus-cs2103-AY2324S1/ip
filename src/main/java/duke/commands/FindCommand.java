package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public String target;

    public FindCommand(String target) {
        this.target = target;
    }

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
