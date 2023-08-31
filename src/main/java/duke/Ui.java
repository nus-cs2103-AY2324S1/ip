package duke;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * UI class that deals with interactions with the user.
 */
public class Ui {

    public Ui() {
    }

    public static void printLine() {
        System.out.println("\t_______________________________________________________________________");
    }

    /**
     * Prints out welcome message at the start of the programme.
     */
    public void welcome() {
        printLine();
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    /**
     * Prints out bye message at the end of the programme.
     */
    public void bye() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Takes in input from the user.
     * @return user input
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        System.out.println("\tHere are the task in your list:");
        tasks.printTasks();
        printLine();
    }

    /**
     * Prints information about the task that has been added to the TaskList object.
     * Also prints the updated number of tasks.
     * @param size updated number of tasks
     * @param task task that has been added
     */
    public void printAddedTask(int size, Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLine();

    }

    /**
     * Prints information about the task that has been marked as done
     * @param index task number
     * @param tasks TaskList object
     */
    public void printAfterMark(int index, TaskList tasks) {
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.getTasks().get(index).toString());
        printLine();
    }

    /**
     * Prints information about the task that has been marked as undone
     * @param index task number
     * @param tasks TaskList object
     */
    public void printAfterUnmark(int index, TaskList tasks) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t  " + tasks.getTasks().get(index).toString());
        printLine();
    }

    /**
     * Prints out information about the task that has been deleted.
     * @param size updated number of tasks
     * @param removedTask task that has been removed
     */
    public void printAfterDelete(int size, Task removedTask) {
        printLine();
        System.out.println("\t  " + removedTask.toString());
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLine();

    }

    /**
     * Prints out information about the task that contains a specified keyword.
     * @param taskList an ArrayList of task that contain the keyword
     */
    public void printMatchingTasks(ArrayList<Task> taskList) {
        printLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.get(i).toString());
        }
        printLine();
    }
}
