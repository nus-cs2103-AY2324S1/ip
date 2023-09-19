package uke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import uke.task.Task;
import uke.task.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Generates Uke's response to the list or find command.
     *
     * @param storedTasks TaskList to be printed.
     * @param isListCommand Whether the method is called from a list command.
     * @return String representation of Uke's response which includes the TaskList or the empty list response.
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
     * Generates Uke's response to the mark command.
     *
     * @param t Task that was marked as done.
     * @return String representation of Uke's response which includes the task that was marked as done.
     */
    public String printMark(Task t) {
        return "Okay! I have marked the following task as DONE:\n" + t;
    }

    /**
     * Generates Uke's response to the unmark command.
     *
     * @param t Task that was marked as undone.
     * @return String representation of Uke's response which includes the task that was marked as undone.
     */
    public String printUnmark(Task t) {
        return "Okay! I have marked the following task as UNDONE:\n" + t;
    }

    /**
     * Generates Uke's response to the delete command.
     *
     * @param t Task that was deleted.
     * @return String representation of Uke's response which includes the task that was deleted.
     */
    public String printDelete(Task t, TaskList tl) {
        return String.format("Okay! The following task has been deleted:\n%s\n%s", t, printNumberOfTasks(tl));
    }

    /**
     * Generates a message indicating the number of tasks in the given TaskList.
     *
     * @param tl TaskList given.
     * @return String representation of a message indicating the number of tasks in the given TaskList.
     */
    public String printNumberOfTasks(TaskList tl) {
        int len = tl.getLength();
        return "You now have " + len + " task(s) in your list.";
    }

    /**
     * Generates Uke's response to the exit command.
     *
     * @return String representation of the exit message.
     */
    public String printExit() {
        String exit = "Bye! See you soon! :)\n";
        String closingMessage = "I will close automatically in 5 seconds.\n";
        return String.format("%s%s", exit, closingMessage);
    }

    /**
     * Generates the string representation of the given error.
     *
     * @param e Exception which error message is to be printed.
     * @return String representation of the error.
     */
    public String printError(Exception e) {
        return e.toString();
    }

    /**
     * Generates Uke's response to the commands that add tasks to the task list.
     *
     * @param t Task that was added.
     * @param tl TaskList to which the task was added.
     * @return String representation of Uke's response which includes the task that was added.
     */
    public String printAdd(Task t, TaskList tl) {
        return String.format("Okay! The following task has been added:\n%s\n%s", t, printNumberOfTasks(tl));
    }

    /**
     * Generates Uke's response to the view command.
     *
     * @param date Date for which the schedule is generated.
     * @param tasks TaskList containing the tasks that start on or is due by the given date.
     * @return String representation of Uke's response which includes the TaskList given.
     */
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
