package command;

import storage.FileHandler;
import storage.TaskList;

import ui.Ui;

/**
 * A command to list the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a `ListCommand` object.
     */
    public ListCommand() {
    }

    /**
     * Executes the list command, which displays all the tasks in the task list.
     *
     * @param t  The task list to retrieve tasks from.
     * @param ui The user interface to display the list of tasks.
     * @param f  The file handler (not used in this command).
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.list();
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
