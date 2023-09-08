package duke;

public class Ui {
    /**
     * Greets the user.
     */
    public static String greet(Tasklist tasks) {
        return "I'm Jermbot. What you want?\n\nHere's your leftover tasks:\n" + listTasks(tasks);
    }

    /**
     * Says goodbye to the user.
     */
    public static String goodbye() {
        return "Good riddance.\n";
    }

    /**
     * Lists out each task in the Tasklist.
     *
     * @param tasks The Tasklist to be listed out.
     * @return String representing the tasklist.
     */
    public static String listTasks(Tasklist tasks) {
        if (tasks.getSize() == 0) {
            return "List is empty. Congrats.";
        } else {
            String response = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                response += (i + 1) + ". " + tasks.getItemString(i) + "\n";
            }
            return response;
        }
    }

    /**
     * Notifies the user that the task has been marked as done.
     *
     * @param task The task that is marked as done.
     * @return String informing user of a task being marked as done.
     */
    public static String informTaskDone(Task task) {
        return "Ok good job lor you finished this task:\n    " + task.toString() + "\n";
    }

    /**
     * Notifies the user that the task has been marked as undone.
     *
     * @param task The task that is marked as undone.
     * @return String informing user of a task being marked as undone.
     */
    public static String informTaskUndone(Task task) {
        return "Wah why you never do this task:\n    " + task.toString() + "\n";
    }

    /**
     * Notifies the user that the task has been deleted.
     *
     * @param task The task that is deleted.
     * @return String informing user of a task being removed.
     */
    public static String informTaskDeleted(Task task, int size) {
        return "Ok slacker I've removed this task:\n    "
                + task.toString() + "\nNow you have "
                + size + " tasks in the list. Happy anot.\n";
    }

    /**
     * Notifies the user that the task has been added.
     *
     * @param task The task that is added.
     * @return String informing user of a task being added.
     */
    public static String informTaskAdded(Task task, int size) {
        return "Haha now you have this task to do:\n   "
                + task.toString() + "\nIn total you have "
                + size + " things to do.\n";
    }
}
