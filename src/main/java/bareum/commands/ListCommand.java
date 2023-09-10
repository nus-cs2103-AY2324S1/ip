package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for listing out all the tasks in the task list.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * List all the tasks in the task list.
     *
     * @param ui Lists all the tasks in the task list.
     * @param storage Storage is not used in this method.
     * @param taskList Task list to list tasks from.
     * @return Response to user input.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        String response = "Here are your current tasks!\n";
        for (int i = 0; i < taskList.size(); i++) {
            response += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return response;
    }
}
