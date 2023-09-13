package seedu.duke;
import java.util.ArrayList;

/**
 * Encapsulates the Ui class.
 * The Ui is responsible for the outputs printed to the user.
 */
public class Ui {
    /**
     * Creates a Ui instance.
     */
    public Ui() {}

    /**
     * Returns a string containing the tasks in the taskList given.
     *
     * @param taskList The tasks accumulated.
     */
    public String getListItemsString(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String message = "";

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            message += (i+1) + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name
                                    + " " + task.getTimeInfo() + "\n";
        }

        return message;
    }

    /**
     * Returns a string containing the message for when a new task is added.
     *
     * @param task The newly added task.
     * @param tasks The current accumulated tasks.
     */
    public String getAddTaskMessage(Task task, TaskList tasks) {
        int listSize = tasks.getTasks().size();

        String message = " Got it. I've added this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + listSize + " tasks in the list.";

        return message;
    }

    /**
     * Returns a string containing the message for when a task is deleted.
     *
     * @param task The newly deleted task.
     * @param tasks The current accumulated tasks.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks){
        int listSize = tasks.getTasks().size();

        String message = " Noted. I've removed this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + listSize + " tasks in the list.";

        return message;
    }

    /**
     * Returns a string containing the message at the end of the program.
     */
    public String getExitMessage() {
        String exitMessage = " Bye. Hope to see you again soon!";

        return exitMessage;
    }

    /**
     * Returns a string containing the message when a task is marked.
     *
     * @param task The task which has been marked.
     */
    public String getMarkedTaskMessage(Task task) {
        String message = " Nice! I've marked this task as done:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo();

        return message;
    }

    /**
     * Returns a string containing the message when a task is marked.
     *
     * @param task The task which has been marked.
     */
    public String getUnmarkedTaskMessage(Task task) {
        String message = " OK. I've marked this task as not done yet:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo();

        return message;
    }

    /**
     * Returns a string containing the results matching the keyword given by the user.
     *
     * @param results The ArrayList<Task> containing Tasks that match the keyword given.
     */
    public String getFindResults(ArrayList<Task> results) {
        String message = " Here are the matching tasks in your list:\n";

        for (int i = 0; i < results.size(); i++) {
            Task task = results.get(i);
            message += "     " + (i+1) + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name +
                    " " + task.getTimeInfo() + "\n";
        }

        return message;
    }

    /**
     * Returns a string containing the message when a task is postponed.
     *
     * @param task The postponed task.
     * @return A string representing hte postpone task message.
     */
    public String getPostponeMessage(Task task) {
        String message = " OK. I've changed the deadline of this task:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo();

        return message;
    }
}
