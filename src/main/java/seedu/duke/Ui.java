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
     * Prints a data format error.
     */
    public void showLoadingError() {
        System.out.println(" ☹ OOPS!!! The saved data format is invalid!");
    }

    /**
     * Prints the tasks in the taskList given.
     *
     * @param taskList The tasks accumulated.
     */
    public String printListItems(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String message = "";

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            message += (i+1) + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name
                                    + " " + task.getTimeInfo() + "\n";
        }
        System.out.println(message);
        return message;
    }

    /**
     * Prints the message for when a new task is added.
     *
     * @param task The newly added task.
     * @param tasks The current accumulated tasks.
     */
    public String printAddTaskMessage(Task task, TaskList tasks) {
        int listSize = tasks.getTasks().size();

        String message = " Got it. I've added this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + listSize + " tasks in the list.";

        System.out.println(message);
        return message;
    }

    /**
     * Prints the message for when a task is deleted.
     *
     * @param task The newly deleted task.
     * @param tasks The current accumulated tasks.
     */
    public String printDeleteTaskMessage(Task task, TaskList tasks){
        int listSize = tasks.getTasks().size();

        String message = " Noted. I've removed this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + listSize + " tasks in the list.";

        System.out.println(message);
        return message;
    }

    /**
     * Prints the message at the end of the program.
     */
    public String printExit() {
        String exitMessage = " Bye. Hope to see you again soon!";

        System.out.println(exitMessage);
        return exitMessage;
    }

    /**
     * Prints the message when a task is marked.
     *
     * @param task The task which has been marked.
     */
    public String printMarkedTaskMessage(Task task) {
        String message = " Nice! I've marked this task as done:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo();

        System.out.println(message);
        return message;
    }

    /**
     * Prints the message when a task is marked.
     *
     * @param task The task which has been marked.
     */
    public String printUnmarkedTaskMessage(Task task) {
        String message = " OK. I've marked this task as not done yet:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo();

        System.out.println(message);
        return message;
    }

    /**
     * Prints the results matching the keyword given by the user.
     *
     * @param results The ArrayList<Task> containing Tasks that match the keyword given.
     */
    public String printFindResults(ArrayList<Task> results) {
        String message = " Here are the matching tasks in your list:\n";

        for (int i = 0; i < results.size(); i++) {
            Task task = results.get(i);
            message += "     " + (i+1) + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name +
                    " " + task.getTimeInfo() + "\n";
        }

        System.out.println(message);
        return message;
    }

    /**
     * Prints the message when a task is postponed.
     *
     * @param task The postponed task.
     * @return A string representing hte postpone task message.
     */
    public String printPostponeMessage(Task task) {
        String message = " OK. I've changed the deadline of this task:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo();

        System.out.println(message);
        return message;
    }
}
