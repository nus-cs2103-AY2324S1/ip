package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents an AddCommand where this command should add into Tasks.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD_D = "deadline";
    public static final String COMMAND_WORD_E = "event";
    public static final String COMMAND_WORD_T = "todo";

    private final Task task;

    /**
     * Public constructor for AddCommand
     *
     * @param task the Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        // Check for duplicated task
        for (Task task: tasks.getAll()) {
            if (task.equals(this.task)) {
                return ui.showDuplicateTask();
            }
        }

        tasks.add(this.task);
        storage.save(tasks);
        String output = "";
        if (!isRestoring) {
            output += ui.showAddTask(this.task, tasks.size());
        }

        return output;
    }
}
