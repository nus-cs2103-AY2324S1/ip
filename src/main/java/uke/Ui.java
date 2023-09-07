package uke;

import uke.task.Task;
import uke.task.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner input = new Scanner(System.in);

    /**
     * Prints the list of tasks in the TaskList given.
     *
     * @param storedTasks TaskList to be printed.
     * @param isListCommand Whether the method is called from a list command.
     */
    public String printList(TaskList storedTasks, boolean isListCommand) {
        int len = storedTasks.getLength();
        String printedList = "";

        if (isListCommand) {
            if (len == 0) {
                return "No tasks found!";
            }

            printedList = "Okay! Here's the full list of your added tasks:\n";
            for (int i = 1; i < len + 1; i++) {
                printedList += i + ". " + storedTasks.getTask(i - 1) + "\n";
            }
        } else {
            if (len == 0) {
                return "No matching tasks found :(";
            }

            printedList = "Here's the list of matching tasks I found:\n";
            for (int i = 1; i < len + 1; i++) {
                printedList += i + ". " + storedTasks.getTask(i - 1) + "\n";
            }
        }

        return printedList;
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param t Task that was marked as done.
     */
    public String printMark(Task t) {
        return "The following task has been marked as done:\n" + t;
    }

    /**
     * Prints the task that was marked as undone.
     *
     * @param t Task that was marked as undone.
     */
    public String printUnmark(Task t) {
        return "The following task has been marked as undone:\n" + t;
    }

    /**
     * Prints the task that was deleted.
     *
     * @param t Task that was deleted.
     */
    public String printDelete(Task t, TaskList tl) {
        return String.format("The following task has been successfully deleted:\n%s\n%s", t, printNumberOfTasks(tl)) ;
    }

    /**
     * Prints the number of tasks in the given TaskList.
     *
     * @param tl TaskList given.
     */
    public String printNumberOfTasks(TaskList tl) {
        int len = tl.getLength();
        return "You now have " + len + " task(s) in your list.";
    }

    /**
     * Prints the exit message.
     */
    public String printExit() {
        String exit = "Bye. See you soon! :)\n";
        return exit;
    }

    /**
     * Prints the error message.
     *
     * @param e Exception which error message is to be printed.
     */
    public String printError(Exception e) {
        return e.toString();
    }

    /**
     * Prints the task that was added.
     *
     * @param t Task that was added.
     */
    public String printAdd(Task t, TaskList tl) {
        return String.format("The following task has been successfully added:\n%s\n%s", t, printNumberOfTasks(tl)) ;
    }
}
