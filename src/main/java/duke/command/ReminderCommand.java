package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * The ReminderCommand represents a command to list the reminders of the task list.
 */
public class ReminderCommand extends Command {

    /**
     * Constructs a ReminderCommand object with the specified task.
     *
     * @param task The task associated with the command.
     */
    public ReminderCommand(Task task) {
        super(task);
    }

    /**
     * Executes the command to display the list of reminders.
     *
     * @param tasks The task list to interact with.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving or loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showReminders(tasks.getReminders());
    }
}
