package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

/**
 * A class for the command for listing all the tasks in the list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        String result = "JonBird:\n\tHere are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            result += ("\n\t\t" + (i + 1) + ". " + taskList.getTask(i).printTask());
        }
        return result;
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
