package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

/**
 * Represents a command to list all tasks.
 * This command, when executed, will return a string representing
 * the list of all tasks the user has.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * This method will return a string representing the list of all tasks
     * the user has. If there are no tasks, it will return a message indicating
     * that the task list is empty.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return tasks.printTaskList();
    }
}
