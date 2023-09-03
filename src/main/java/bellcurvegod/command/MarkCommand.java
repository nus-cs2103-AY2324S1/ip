package bellcurvegod.command;

import bellcurvegod.task.Task;
import bellcurvegod.ui.Ui;

public class MarkCommand implements Runnable {
    /**
     * Marks the given task as done.
     * @param task task to be marked as done.
     */
    public static void run(Task task) {
        Ui.showLine();
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        Ui.showLine();
    }
}
