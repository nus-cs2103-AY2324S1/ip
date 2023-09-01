package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Todo;

/**
 * For executing the to do command.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Create the TodoCommand.
     *
     * @param description The description for the To Do.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Create a To Do, store it in Tasklist and save it.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

        Todo t = new Todo(this.description);

        tasks.addTask(t);

        ui.showAddTask(t, tasks.size());

        storage.save(tasks);
    }
}
