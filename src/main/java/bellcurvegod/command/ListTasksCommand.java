package bellcurvegod.command;

import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;
import bellcurvegod.ui.Ui;

import java.util.ArrayList;

public class ListTasksCommand implements Runnable {
    /**
     * List all tasks stored.
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
