package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

/**
 * Represents a command to add a new deadline.
 * This command, when executed, will parse the user input and
 * create a new deadline task, which is added to the task list.
 */
public class DeadlineCommand extends Command {

    private final String command;

    /**
     * Initializes a new instance of the DeadlineCommand class.
     *
     * @param command The entire command string input by the user.
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the deadline command.
     * This method will parse the command string, create a new deadline task, and
     * add it to the task list. If the command string is invalid, it will return
     * an error message.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        assert command != null : "Should have an input!";
        String[] commandList = command.split("deadline ");

        if (commandList.length == 1) {
            return ("Invalid! The description of a deadline cannot be empty.");
        } else {
            String[] deadlineHelper = commandList[1].split(" /by ");
            if (deadlineHelper.length == 2) {
                String returnStr = tasks.addDeadline(deadlineHelper[0], deadlineHelper[1]);
                storage.writeToFile(tasks.getTaskAsList());
                return returnStr;
            } else {
                return ("Invalid! The description of a deadline cannot be empty.");
            }
        }
    }
}
