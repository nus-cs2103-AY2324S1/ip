package chadbod;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {
    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        System.out.println("Hello! I'm ChadBod.");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message to the console.
     *
     * @param str The error message to be displayed.
     */
    public void printErrorMessage(String str) {
        System.out.println(str);
    }

    /**
     * Prints a status update message for a task.
     *
     * @param done If true, the task is marked as done; if false, marked as not done.
     * @param task The task for which the status update is displayed.
     */
    public void printStatusUpdate(Boolean done, Task task) {
        if (done) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.printf("%s\n", task);
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param newTask   The task that has been added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void printTaskAddedMessage(Task newTask, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    /**
     * Prints a message indicating that a task has been removed.
     *
     * @param removedTask The task that has been removed.
     * @param taskCount   The total number of tasks in the list after removing the task.
     */
    public void printTaskRemovedMessage(Task removedTask, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("%s\n", removedTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    /**
     * Prints the list of tasks to the console.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void printTasks(TaskList tasks) {
        System.out.print(tasks);
    }
}