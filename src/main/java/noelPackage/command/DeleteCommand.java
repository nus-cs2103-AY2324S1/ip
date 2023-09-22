package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;
import noelPackage.tasks.Task;

/**
 * Represents a command to delete a task.
 * This command, when executed, will remove a specified task from the task list.
 */
public class DeleteCommand extends Command {

    private final String command;

    /**
     * Initializes a new instance of the DeleteCommand class.
     *
     * @param command The entire command string input by the user.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the delete command.
     * This method will parse the command string, remove the specified task from the task list,
     * and return a confirmation message. If the command string is invalid, it will return an error message.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        assert command != null : "Should have an input!";
        String[] commandInputs = this.command.split("delete ");

        if (commandInputs.length == 1) {
            return ("OOPS!!! The description of a delete cannot be empty.");
        } else {
            int intToRemove = Integer.parseInt(commandInputs[1]) - 1;
            Task taskToDel = tasks.getTask(intToRemove);
            tasks.remove(intToRemove);

            String delStart = "Noted. I've removed this task:\n";
            String delEnd = "Now you have " + tasks.getSize() + " tasks in the list.";
            storage.writeToFile(tasks.getTaskAsList());
            return (delStart + taskToDel + "\n" + delEnd);
        }
    }
}
