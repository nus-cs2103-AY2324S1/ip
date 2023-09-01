package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param taskToAdd The task to be added to the task list.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the AddCommand to add the task to the task list, update the user interface,
     * and save the tasks to storage.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks.
     * @throws DukeException If there is an error while executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(taskToAdd);
        ui.showAdded(taskToAdd, tasks.listSize());
        storage.saveTasks(tasks);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since AddCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}