package duke.commands;

import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a task to a task list.
 * This command, when executed, will add a specified task to the given task list
 * and save the updated list using the provided storage mechanism.
 */
public class AddTaskCommand extends Command {

    /** The task to be added when this command is executed. */
    private final Task task;

    /**
     * Constructs an AddTaskCommand with the specified task to be added.
     *
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddTaskCommand by adding the task to the provided task list,
     * saving the updated list, and generating an appropriate response for the user.
     *
     * @param tasks The task list to which the task should be added.
     * @param ui The UI to which responses will be added.
     * @param storage The storage mechanism used to save the updated task list.
     * @throws Exception If there are issues saving the updated task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.addTask(task);

        storage.save(tasks);

        String format = "Got it. I've added this task:\n%s\n" + "Now you have %d tasks in the list.";
        String message = String.format(format, task, tasks.getNumberOfTasks());

        ui.addToResponse(message);
    }
}

