package bellcurvegod.command;

import java.util.ArrayList;

import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;
import bellcurvegod.ui.Ui;

/**
 * Encapsulates the findCommand.
 */
public class FindCommand implements Runnable {
    /**
     * Lists all tasks in the taskList that
     * contains the keyword that the user is searching for.
     */
    public static void run(String keyword) {
        ArrayList<Task> tasks = TaskList.getTaskList();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        Ui.showFindMessage(matchingTasks);
    }
}
