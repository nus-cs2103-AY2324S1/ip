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
        Ui.showLine();
        ArrayList<Task> tasks = TaskList.getTaskList();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        if (!matchingTasks.isEmpty()) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        } else {
            System.out.println("There is no task in your list that matches this keyword.");
        }

        Ui.showLine();
    }
}
