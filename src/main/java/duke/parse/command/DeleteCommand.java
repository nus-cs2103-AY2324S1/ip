package duke.parse.command;

import duke.Duke;

/**
 * Represents a command to delete the task.
 */
public class DeleteCommand implements Command {
    private int taskIndex;

    /**
     * Instantiates the command to delete the task with the given index.
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Commands the bot to delete the task with the given index.
     * @param bot the bot to execute the command
     * @return true, as this allows the user to continue the programme
     */
    @Override
    public boolean execute(Duke bot) {
        bot.deleteTask(this.taskIndex);
        return true;
    }

    /**
     * Checks whether this delete command is the same as another command, for testing purposes.
     * It is equal if the tasks to be deleted are the same.
     * @param another the object to compare with
     * @return whether this delete command is the same as another
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof DeleteCommand) {
            DeleteCommand anotherDelete = (DeleteCommand) another;
            return this.taskIndex == anotherDelete.taskIndex;
        }
        return false;
    }
}
