package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all the tasks in the Duke application.
 */
public class ListCommand extends Command {

    /**
     * Executes the list task command.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     * @throws DukeException If there's an error while parsing the user input or updating the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        displayList(taskList, ui);
    }

    private static void displayList(TaskList taskList, Ui ui) {
        String str = "Here are the tasks in your list:\n\t ";
        str = getListString(taskList, str);
        ui.sendMessage(str.substring(0, str.length() - 3));
    }

    private static String getListString(TaskList taskList, String str) {
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            str += count + 1 + "." + task.toString() + "\n\t ";
            count++;
        }
        return str;
    }

    /**
     * Indicates whether this command is an exit command.
     * ListCommand is not an exit command, so this method returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
