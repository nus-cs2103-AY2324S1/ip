package sally;


/**
 * Represents a command to list all tasks in the TaskList.
 * Implements the Command interface to provide the execute method.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by showing all tasks in the TaskList.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @param storage The Storage for tasks (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        //add String below
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        return res;
    }
}
