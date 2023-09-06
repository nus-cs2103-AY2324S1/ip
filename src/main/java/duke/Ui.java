package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    /**
     * Displays a default start message when the program starts.
     *
     * @return Start message.
     */
    public String startMessage() {
        return "I'm Boo, nice to meet you! "
                + "You can start adding your tasks :-)";
    }

    /**
     * Displays the default end message when the user exits the program.
     *
     * @return End message.
     */
    public String endMessage() {
        return "Bye for now, hope to see you soon!";
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task The task that is added.
     * @param totalTasks The total number of tasks in the list after adding.
     * @return String representation of a task being added.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return "Got it. I've added this task:\n  " + task.toString()
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param task The task that is deleted.
     * @param totalTasks The total number of tasks in the list after deleting.
     * @return String representation of a task being deleted.
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     * @return String representation of a task being marked.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + "[X] "
                + task.getDescription();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that is unmarked.
     * @return String representation of a task being unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + "[ ] "
                + task.getDescription();
    }

    /**
     * Displays the user's task list.
     *
     * @param taskList The task list to be displayed.
     * @return String representation of the user's list of task/s.
     */
    public String showList(TaskList taskList) {
        StringBuilder list = new StringBuilder();
        list.append("Here are the tasks in your list:\n");

        for (int i = 1; i <= taskList.getSize(); i++) {
            // Adding toString() to use the overridden one in duke.task.Task, etc.
            list.append(i).append(". ").append(taskList.getTask(i - 1).toString())
                    .append("\n");;
        }

        return list.toString();
    }

    /**
     * Displays the list of matching task from the user's task list.
     *
     * @param matchingTaskList The task list to be displayed.
     * @return String representation of the list of matching task from the user's task list.
     */
    public String showMatchingList(TaskList matchingTaskList) {
        StringBuilder matchingList = new StringBuilder();
        matchingList.append("Here are the matching tasks in your list:\n");

        for (int i = 1; i <= matchingTaskList.getSize(); i++) {
            // Adding toString() to use the overridden one in duke.task.Task, etc.
            matchingList.append(i).append(". ").append(matchingTaskList.getTask(i - 1).toString())
                    .append("\n");
        }

        return matchingList.toString();
    }

}
