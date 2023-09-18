package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents a list command, which will list out all the tasks in the taskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, which will display all the tasks in the current tasklist.
     *
     * @param taskList The current taskList stored in Botty.
     * @param ui The current ui details.
     * @return Result to be displayed to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            String listCounter = (i + 1) + ".";
            str.append(listCounter + currTask.toString() + "\n");
        }
        str.append("\n");
        return str.toString();
    }

}
