package duke;

/**
 * Command class that represents what a user wants to do
 *
 * @author wj331
 */
public abstract class Command {
    /**
     * Executes itself
     * @param tasks the TaskLists that stores all the tasks of the bot
     * @param ui the UI that does all printing messages
     * @param storage the storage that reads and writes info into the file
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException;

    /**
     * Determines if this task should break the while loop
     * @return boolean value false since it should not break
     */
    public abstract boolean isExit();
}
