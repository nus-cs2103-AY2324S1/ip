package duke.ui;

import duke.data.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles user interface interactions for the ChatterChicken task manager application.
 * It provides methods to display messages and task-related information to the user.
 */
public class Ui {
    private static final String INDENT = "      ";
    private static final String INDENT_BIG = "        ";

    /**
     * Displays a greeting message to the user to introduce ChatterChicken.
     */
    public String displayGreeting() {
        return INDENT + "Hello! I'm ChatterChicken!\n" + INDENT + "What can I do for you?";
    }

    /**
     * Displays a farewell message to the user as they exit the ChatterChicken application.
     */
    public String displayFarewell() {
        return INDENT + "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     */
    public String displayAddTask(Task task, int size) {
        return INDENT + "Got it. I've added this task:\n"
                + INDENT_BIG + task.getTaskForPrinting() + "\n"
                + INDENT + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     */
    public String displayDeleteTask(Task task, int size) {
        return INDENT + "Noted. I've removed this task:\n"
                + INDENT_BIG + task.getTaskForPrinting() + "\n"
                + INDENT + "Now you have " + size + " tasks in your list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public String displayMarkTask(Task task) {
         return INDENT + "Nice! I've marked this task as done:\n"
                + INDENT_BIG + task.getTaskForPrinting();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked.
     */
    public String displayUnmarkTask(Task task) {
        return  INDENT + "OK, I've marked this task as not done yet:\n"
                + INDENT_BIG + task.getTaskForPrinting();
    }

    public String displayMatchingTasks(ArrayList<Task> taskList) {
        String result =  INDENT + "Here are the matching tasks in your list:";
        int index = 1;
        for (Task task : taskList) {
            result += "\n" + INDENT_BIG + index + "." + task.getTaskForPrinting();
            index++;
        }
        return result;
    }

    public String displayCompletedTaskCount(int completedCount, int totalCount) {
        return INDENT + "Congratulations! You have completed " + completedCount + " of " + totalCount + " tasks!\n";
    }

    public String displayUncompletedTaskCount(int uncompletedCount, int totalCount) {
        return INDENT + "You still have " + uncompletedCount + " of " + totalCount + " tasks uncompleted. Jia you! \n";
    }

    public String displayTaskTypeCount(int todoCount, int deadlineCount, int eventCount) {
        return INDENT + "Your tasklist consists of :\n" +
                INDENT_BIG + todoCount + " todos \n" +
                INDENT_BIG + deadlineCount + " deadlines \n" +
                INDENT_BIG + eventCount + " events\n";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String displayList(ArrayList<Task> taskList) {
        String result = INDENT + "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            result += "\n" + INDENT_BIG + (i + 1) + "." + taskList.get(i).getTaskForPrinting();
        }
        return result;
    }
}
