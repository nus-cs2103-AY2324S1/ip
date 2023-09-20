package bellcurvegod.command;

import java.util.ArrayList;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the listTaskCommand.
 */
public class ListTasksCommand {
    /**
     * Lists all tasks stored.
     *
     * @return ListMessage.
     */
    public static String run() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        return Gui.getListMessage(tasks);
    }

    public static String getHelpMessage() {
        return "Type 'list' and enter, the app will list all existing tasks.";
    }
}
