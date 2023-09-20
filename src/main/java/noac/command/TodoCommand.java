package noac.command;

import noac.util.Storage;
import noac.util.TaskList;
import noac.util.Ui;
import noac.task.Todo;

/**
 * For executing the to do command.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Creates the TodoCommand.
     *
     * @param description The description for the To Do.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a To Do, store it in Tasklist and save it.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @return String to be displayed to user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage){
        Todo t = new Todo(this.description);

        tasks.addTask(t);

        storage.save(tasks);

        return ui.showAddTask(t, tasks.size());
    }
}
