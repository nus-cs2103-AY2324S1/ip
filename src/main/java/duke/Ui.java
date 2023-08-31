package duke;

import duke.taskClasses.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Constructs a new Ui instance and displays the welcome message.
     */
    public Ui() {
        String logo = " ██▄   ████▄    ▄     ▄▀  \n" +
                "█  █  █   █     █  ▄▀    \n" +
                "█   █ █   █ ██   █ █ ▀▄  \n" +
                "█  █  ▀████ █ █  █ █   █ \n" +
                "███▀        █  █ █  ███  \n" +
                "            █   ██       \n" +
                "                         ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints a message to inform that the task has been marked as done.
     *
     * @param taskContent The content of the task that has been marked as done.
     */
    public void printTaskMarkAsDone(String taskContent) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskContent);
    }

    /**
     * Prints a message to inform that the task has been marked as not done.
     *
     * @param taskContent The content of the task that has been marked as not done.
     */
    public void printTaskMarkAsNotDone(String taskContent) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskContent);
    }

    /**
     * Informs the user that there was an error loading data from storage.
     */
    public void showLoadingError() {
        System.out.println("ERROR reading the file, might be corrupted");
    }

    /**
     * Prints all the tasks available in the provided task list.
     *
     * @param tasks The task list containing tasks to print.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        tasks.printAllStatusAndDescription();
    }

    /**
     * Prints a dashed line for visual separation in the interface.
     */
    public void newDashedLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Informs the user that a task has been deleted.
     *
     * @param content The content of the deleted task.
     */
    public void deleteTask(String content) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(content);
    }

    /**
     * Informs the user about the current count of tasks.
     *
     * @param count The current number of tasks.
     */
    public void printTaskCount(int count) {
        System.out.println(String.format("Now you have %s tasks in the list.", count));
    }

    /**
     * Prints all the tasks available in the provided task list that contains the keyword.
     *
     * @param tasks The task list containing tasks to print.
     */
    public void printTaskContainingKeyword(TaskList tasks, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        tasks.printAllStatusAndDescriptionWithKeyword(keyword);
    }


    /**
     * Prints a goodbye message.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
