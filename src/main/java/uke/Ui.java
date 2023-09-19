package uke;

import uke.task.Task;
import uke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints the list of tasks in the TaskList given.
     *
     * @param storedTasks TaskList to be printed.
     * @param isListCommand Whether the method is called from a list command.
     */
    public String printList(TaskList storedTasks, boolean isListCommand) {
        int len = storedTasks.getLength();

        String successfulListMessage = isListCommand
                ? "Okay! Here's the list of your added tasks:\n"
                : "There you go! Here's the list of matching tasks I found:\n";

        String unsuccessfulListMessage = isListCommand
                ? "No tasks found! :("
                : "No matching tasks found! :(";

        if (len == 0) {
            return unsuccessfulListMessage;
        }

        for (int i = 1; i < len + 1; i++) {
            successfulListMessage += i + ". " + storedTasks.getTask(i - 1) + "\n";
        }

        return successfulListMessage;
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param t Task that was marked as done.
     */
    public String printMark(Task t) {
        return "Okay! I have marked the following task as DONE:\n" + t;
    }

    /**
     * Prints the task that was marked as undone.
     *
     * @param t Task that was marked as undone.
     */
    public String printUnmark(Task t) {
        return "Okay! I have marked the following task as UNDONE:\n" + t;
    }

    /**
     * Prints the task that was deleted.
     *
     * @param t Task that was deleted.
     */
    public String printDelete(Task t, TaskList tl) {
        return String.format("Okay! The following task has been successfully deleted:\n%s\n%s", t, printNumberOfTasks(tl)) ;
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
        String exit = "Bye! See you soon! :)\n";
        String closingMessage = "I will close automatically in 5 seconds.\n";
        return String.format("%s%s", exit, closingMessage);
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
        return String.format("Okay! The following task has been successfully added:\n%s\n%s", t, printNumberOfTasks(tl)) ;
    }

    public String printSchedule(LocalDate date, TaskList tasks) {
        int len = tasks.getLength();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String displayedDate = date.format(dateTimeFormatter);

        if (len == 0) {
            return String.format("Your schedule for %s is currently empty! Add some tasks? :(", displayedDate);
        }

        String schedule = String.format("Okay! :) Here's your schedule for %s:\n", displayedDate);

        for (int i = 0; i < len; i++) {
            schedule += tasks.getTask(i) + "\n";
        }

        return schedule;
    }
}
