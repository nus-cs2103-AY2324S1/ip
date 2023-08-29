import java.util.Scanner;

/**
 * The Ui class handles interactions with the user interface, providing methods to read input,
 * display messages, and print various types of information.
 *
 * @author selwyn
 */
public class Ui {
    /** Name of the chatbot */
    private static String name;

    /**
     * Constructs an Ui object with the specified name to be used in greetings and messages.
     *
     * @param name The name of the application.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command input provided by the user as a string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Prints the task list to the console.
     *
     * @param taskList The task list to be displayed.
     */
    public void printTaskList(TaskList taskList) {
        taskList.displayTaskList();
    }

    /**
     * Prints a message indicating that a task has been added, along with the new task's details.
     *
     * @param addedTask The task that was added.
     * @param noOfTasksAfterAdding The number of tasks after adding the new task.
     */
    public void printAddedTask(Task addedTask, int noOfTasksAfterAdding) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   ");
        System.out.println(addedTask.toString());

        if (noOfTasksAfterAdding == 1) {
            System.out.println("Now you have " + noOfTasksAfterAdding + " task in the list.");
        } else {
            System.out.println("Now you have " + noOfTasksAfterAdding + " tasks in the list.");
        }
    }

    /**
     * Prints a message indicating the change in task done status.
     *
     * @param taskToChange The task for which the status changed.
     * @param isDone Whether the task is marked as done or not done.
     */
    public void printChangeTaskDoneStatus(Task taskToChange, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(taskToChange.toString());
    }

    /**
     * Prints a message indicating that a task has been deleted, along with the remaining tasks count.
     *
     * @param deletedTask The task that was deleted.
     * @param numOfTasksLeft The number of tasks remaining after deletion.
     */
    public void printDeletedTask(Task deletedTask, int numOfTasksLeft) {
        System.out.println("Noted. I've removed this task:");
        System.out.print("   ");
        System.out.println(deletedTask.toString());

        if (numOfTasksLeft == 1 || numOfTasksLeft == 0) {
            System.out.println("Now you have " + numOfTasksLeft + " task in the list.");
        } else {
            System.out.println("Now you have " + numOfTasksLeft + " tasks in the list.");
        }
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        this.printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        this.printHorizontalLine();
    }

    /**
     * Prints a farewell message to the user.
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal line for visual separation.
     */
    public void printHorizontalLine() {
        int width = 50;
        for (int i  = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    /**
     * Prints an error message.
     *
     * @param errorMsg The error message to be displayed.
     */
    public void printError(String errorMsg) {
        System.out.println("OOPS! " + errorMsg);
    }
}