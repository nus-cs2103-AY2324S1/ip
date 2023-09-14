package bellcurvegod.command;

import java.util.ArrayList;

import bellcurvegod.gui.Gui;
import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the listTaskCommand.
 */
public class ListTasksCommand implements Runnable {
    /**
     * Lists all tasks stored.
     */
    public static String run() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        return Gui.getListMessage(tasks);
    }
}
