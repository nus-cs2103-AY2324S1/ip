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
        Ui.showLine();
        ArrayList<Task> tasks = TaskList.getTaskList();
        if (!tasks.isEmpty()) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        } else {
            System.out.println("There is no task in your list.");
        }
        Ui.showLine();
    }
}
