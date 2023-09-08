package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.DukeList;
import duke.task.Task;

/**
 * The ListCommand class represents a command for listing all tasks in the task list.
 *
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand by displaying all tasks in the task list.
     *
     * @param dukelist The task list to be displayed.
     * @param storage  The storage object (not used in this command).
     * @return A message displaying all tasks in the task list.
     */
    @Override
    public String execute(DukeList dukelist, Storage storage) {
        String strToReturn = "Here are the tasks in your list: \n";
        for (int i = 0; i < dukelist.getList().size(); i++) {
            int num = i + 1;
            Task chosenTask = dukelist.getList().get(i);
            strToReturn += (num + ". " + chosenTask.toString() + "\n");
        }
        return strToReturn;
    }
}