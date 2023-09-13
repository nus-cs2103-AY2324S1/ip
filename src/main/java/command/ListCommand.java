package command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * The Command to indicate that the user wants the chatbot to list out the current
 * tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTaskList();
        return ui.printTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
