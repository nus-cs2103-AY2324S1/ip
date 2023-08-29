package seedu.duke;

import java.util.List;
import java.util.Scanner;

import seedu.duke.tasks.Task;

/**
 * UI class
 */
public class Ui {

    /**
     * gets user input into system
     *
     * @return user input
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * prints the greeting message
     */
    public void printGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | | / _ \\\n"
                + "| |_| | |_| |  |_   __/\n"
                + "|____/ \\__,_|___|\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke!\nWhat can I do for you?\n");
    }

    /**
     * prints exit message
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * prints Tasks of specfic date
     * @param taskList task list to print
     */
    public void printTasksOnDate(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println(taskList.size() + " task: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    /**
     * Prints details when adding task to TaskList
     *
     * @param task Task to add
     * @param taskList TaskList to operate on
     */
    public void printAddingTask(Task task, List<Task> taskList) {
        String message = String.format("Got it. I've added this task:\n  "
                + task
                + "\nNow you have %s tasks in the list\n", taskList.size());
        System.out.println(message);
    }

    /**
     * Prints all Tasks in a given TaskList
     *
     * @param taskList TaskList to operate on
     */
    public void printAllTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
            System.out.println("");
        }
    }

    /**
     * prints details when deleting a task
     *
     * @param task Task to delete
     */
    public void printDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:" + "\n  " + task + "\n");
    }

    /**
     * prints while marking a task
     *
     * @param task task to mark
     * @param beforeMarking if task was marked before any operation
     */
    public void printMarkTask(Task task, boolean beforeMarking) {
        if (beforeMarking) {
            System.out.println("Error! Task already marked!\n");
        } else {
            System.out.println("Nice! I've marked this task as done:\n  " + task + "\n");
        }
    }

    /**
     * prints while unmarking of a task
     * @param task Task to unmark
     * @param beforeMarking if task was already unmark before any operation
     */
    public void printUnMarkTask(Task task, boolean beforeMarking) {
        if (beforeMarking) {
            System.out.println("I've unmarked this task:\n  " + task + "\n");
        } else {
            System.out.println("Error! Task already unmarked\n");
        }
    }
}
