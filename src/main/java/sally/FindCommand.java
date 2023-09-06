package sally;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand implements Command {
    private String keyword;
    private Message message;

    /**
     * Constructs a FindCommand with the specified keyword to search for.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command. Searches for tasks that match the provided keyword
     * in the given task list and displays the matching tasks using the provided UI.
     *
     * @param tasks   The list of tasks to search through.
     * @param storage The storage to save tasks after unmarking.
     * @throws SallyException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        message = new Message();
        TaskList matchingTasks = tasks.findTasks(keyword);
        return message.findMessage(matchingTasks);
    }
}
