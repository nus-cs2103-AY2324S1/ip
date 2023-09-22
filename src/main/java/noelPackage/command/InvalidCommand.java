package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

/**
 * Represents a command that is not recognized as valid.
 * This command, when executed, will return a message indicating
 * that the input command is invalid.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command.
     * This method will return a string indicating that the entered
     * command is invalid.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return "Invalid Command!";
    }
}
