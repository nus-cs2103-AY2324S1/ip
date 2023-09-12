package sally;


/**
 * Represents a command to list all tasks in the TaskList.
 * Implements the Command interface to provide the execute method.
 */
public class ListCommand implements Command {

    private Message message;

    /**
     * Executes the list command by showing all tasks in the TaskList.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @param storage The Storage for tasks (not used in this command).
     * @return A string indicating all tasks have been displayed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        message = new Message();
        return message.listMessage(tasks);
    }
}
