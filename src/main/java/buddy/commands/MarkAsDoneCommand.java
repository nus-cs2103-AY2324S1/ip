package buddy.commands;

import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for marking a task in the task list as done.
 */
public class MarkAsDoneCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "mark <task index>\n" + "Example: mark 2";
    public static final String MESSAGE_INDEX_BOUND =
            "Index from 1 to ";
    private int index;

    /**
     * The constructor for a MarkAsDoneCommand.
     *
     * @param index The zero-based index of the task.
     */
    public MarkAsDoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(index);
        String response = ui.printMessage("NICE! I've marked this task as done:\n"
                + tasks.getTask(index).toString());
        storage.writeToFile(tasks.getAllTasks());
        return response;
    }
}
