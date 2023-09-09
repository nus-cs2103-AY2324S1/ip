package duke.commands;

import java.util.ArrayList;
import java.util.List;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents an FindCommand where the user is finding tasks based on a target string.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String target;

    /**
     * Public constructor for FindCommand
     *
     * @param target the targeted string that the user is searching for
     */
    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        assert !this.target.equals("") : "target string to find shouldn't be empty!";

        List<Task> foundTasks = new ArrayList<>();
        for (Task task: tasks.getAll()) {
            if (task.getDescription().contains(this.target)) {
                foundTasks.add(task);
            }
        }
        String output = "";
        if (foundTasks.size() > 0) {
            output += ui.showTasks(foundTasks, true);
        } else {
            output += ui.showNoTasks();
        }

        return output;
    }
}
