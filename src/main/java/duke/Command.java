package duke;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks tasks in the task list.
     * @param ui ui that interacts with users.
     * @param storage storage to save tasks.
     * @throws Exception  if there is an error.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

}
