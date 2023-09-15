package chadbod;

/**
 * The Ui class handles user interface interactions by returning messages as strings.
 */
public class Ui {
    /**
     * Returns a welcome message as a string.
     */
    public static String displayGreeting() {
        String greetingString = "Hello! I'm ChadBod. \nWhat can I do for you?";
        return greetingString;
    }
    /**
     * Returns a farewell message as a string.
     */
    public String displayFarewell() {
        String farewellString = "Bye. Hope to see you again soon!";
        return farewellString;
    }

    /**
     * Returns an error message as a string.
     *
     * @param str The error message to be generated.
     */
    public String displayErrorMessage(String str) {
        return str;
    }

    /**
     * Returns a status update message for a task as a string.
     *
     * @param done If true, the task is marked as done; if false, marked as not done.
     * @param task The task for which the status update message is generated.
     */
    public String displayStatusUpdate(Boolean done, Task task) {
        assert task != null : "No task given to displayStatusUpdate method.";
        if (done) {
            StringBuilder s = new StringBuilder("Nice! I've marked this task as done:\n");
            s.append(task).append("\n");
            return s.toString();
        } else {
            return "OK, I've marked this task as not done yet:\n" + task + "\n";
        }
    }

    /**
     * Returns a message indicating that a given task has been added as a string, as well as the new task count.
     *
     * @param newTask   The task that has been added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public String displayTaskAddedMessage(Task newTask, int taskCount) {
        assert newTask != null : "No task given to displayTaskAddedMessage method.";
        StringBuilder s = new StringBuilder("Got it. I've added this task:\n");
        s.append(newTask).append("\n");
        s.append("Now you have ").append(taskCount).append(" tasks in the list.\n");
        return s.toString();
    }

    /**
     * Returns a message indicating that a given task has been removed as a string, as well as the new task count.
     *
     * @param removedTask The task that has been removed.
     * @param taskCount   The total number of tasks in the list after removing the task.
     */
    public String displayTaskRemovedMessage(Task removedTask, int taskCount) {
        assert removedTask != null : "No task given to displayTaskRemovedMessage method.";
        StringBuilder s = new StringBuilder("Noted. I've removed this task:\n");
        s.append(removedTask).append("\n");
        s.append("Now you have ").append(taskCount).append(" tasks in the list.\n");
        return s.toString();
    }

    /**
     * Returns the string representation of the given list of tasks.
     *
     * @param tasks The list of tasks to be represented as a string.
     */
    public String displayTasks(TaskList tasks) {
        return tasks.toString();
    }
}
