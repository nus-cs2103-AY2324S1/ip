package iris;

/**
 * A class that handles the user interface of the Iris application.
 */
public class Ui {
    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String respond(String message) {
        return message;
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getTasksMessage(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.toString();
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String markTaskMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String unmarkTaskMessage(Task task) {
        return "Okay, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getKeywordTasksMessage(TaskList keywordTaskList) {
        return "Here are the matching tasks in your list: \n" + keywordTaskList.toString();
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getNoKeywordTasksFoundMessage() {
        return "No matching tasks found.";
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getAddTaskMessage(TaskList taskList, Task task) {
        return String.format("      Got it. I've added this task:\n          " +
                "%s\n      Now you have " +
                "%d tasks in the list", task, taskList.getSize());
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getDeleteTaskMessage(TaskList taskList, Task task) {
        return String.format("Noted. I've removed this task:\n          " +
                "%s\n      Now you have" +
                " %d tasks in the list", task, taskList.getSize());
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getPostponeTaskMessage(Task postponedTask) {
        return "OK, I have postponed this task:\n" + postponedTask;
    }

    /**
     * Returns a string representation of the welcome message.
     *
     * @return A string describing the welcome message.
     */
    public String getUnableToPostponeTaskMessage() {
        return "Task selected is not time-sensitive! Please select carefully!";
    }
}
