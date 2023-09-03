package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
/**
 * The `ListoutCommand` class represents a command to list out and display tasks from the task list.
 * When executed, it instructs the user interface to display the list of tasks.
 */
public class ListoutCommand extends Command {

    /**
     * Executes the `ListoutCommand` by instructing the user interface to display the list of tasks.
     *
     * @param tasks   The task list containing the tasks to be listed.
     * @param ui      The user interface responsible for displaying the list of tasks.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.listout(tasks);
    }
}

