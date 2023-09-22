package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

/**
 * Represents a command to terminate the program.
 * When executed, it will display a farewell message to the user.
 */
public class ByeCommand extends Command {

    static String BYE_MSG = " Bye. Hope to see you again soon!";

    /**
     * Executes the bye command.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the farewell message to be displayed to the user.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return BYE_MSG;
    }
}
