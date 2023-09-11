package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find tasks in the task list that match a given search query.
 */
public class FindCommand extends Command {
    private String searchItem;

    /**
     * Constructs a new FindCommand with the specified search query.
     *
     * @param searchItem The search query entered by the user.
     */
    public FindCommand(String searchItem) {
        this.searchItem = searchItem;
    }

    /**
     * Executes the find command to search for tasks in the task list that contain
     * the search query and displays the matching tasks.
     *
     * @param taskList The TaskList containing all tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving and loading tasks (not used in this command).
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        findTask(taskList, ui);
    }

    private void findTask(TaskList taskList, Ui ui) {
        String str = "Here are the tasks in your list:\n\t ";
        str = getFindTaskString(taskList, str);
        ui.sendMessage(str.substring(0, str.length() - 3));
    }

    private String getFindTaskString(TaskList taskList, String str) {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.toString().contains(this.searchItem)) {
                str += count + 1 + "." + task + "\n\t ";
                count++;
            }
        }
        return str;
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return False, as the find command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
