package duke.parse.command;

import duke.Duke;
import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * Instantiates a command to add the given task
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Commands the bot to add the task to its list.
     * @param bot The bot to add the task.
     * @return true, as it allows the user to continue the programme.
     */
    @Override
    public boolean execute(Duke bot) {
        bot.addTaskToList(task);
        return true;
    }

    /**
     * Checks whether this command is the same as another, for testing purposes.
     * It is equal if the tasks to be added are the same.
     * @param another The object to compare against.
     * @return Whether this command is the same as another.
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof AddCommand) {
            AddCommand anotherAdd = (AddCommand) another;
            return this.task.equals(anotherAdd.task);
        }
        return false;
    }
}
