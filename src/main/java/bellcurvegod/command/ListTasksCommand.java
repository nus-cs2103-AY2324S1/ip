package bellcurvegod.command;

import java.util.ArrayList;

import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;
import bellcurvegod.ui.Ui;

/**
 * Encapsulates the listTaskCommand.
 */
public class ListTasksCommand implements Runnable {
    /**
     * Lists all tasks stored.
     */
    public static void run() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        Ui.showListMessage(tasks);
    }
}
