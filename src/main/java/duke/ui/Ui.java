package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The user interface for the Chat Bot.
 *
 * @author Armando Jovan Kusuma
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String MESSAGE = LINE + "\n"
            + " Hello! I'm ChatBot\n"
            + " What can I do for you?\n"
            + LINE + "\n";

    /**
     * Greets the user at the start
     */
    public String greet() {

        return MESSAGE;
    }

    /**
     * Prints a message if a task is added to the list
     *
     * @param input the task added to the list of tasks
     * @param count the number of tasks in the list
     */
    public String taskPrint(Task input, int count) {
        return LINE + "\n" + "Got it. I've added this task" + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list." + "\n" + LINE;
    }

    /**
     * Prints a message if a task is deleted from the list
     *
     * @param input the task deleted from the list of tasks
     * @param count the number of tasks in the list
     */
    public String deletePrint(Task input, int count) {
        return LINE + "\n" + "Noted. I've removed this task: " + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list." + "\n" + LINE;
    }

    /**
     * Prints a message if a task is marked as done.
     *
     * @param task the task to be marked as done
     */
    public String markDonePrint(Task task) {
        return LINE + "\n" + "Nice! I've marked this task as done: " + "\n" + task + "\n" + LINE;
    }

    /**
     * Prints a message if a task is unmarked as done.
     *
     * @param task the task to be unmarked as done
     */
    public String unmarkDonePrint(Task task) {
        return LINE + "\n" + "OK, I've marked this task as not done yet: " + "\n" + task + "\n" + LINE;
    }

    /**
     * Prints a message if a task is marked as done.
     */
    public String bye() {
        return LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + LINE;
    }

    /**
     * Prints the list of tasks
     *
     * @param tasksList the list of tasks
     * @param count the amount of tasks in the tasks list
     */
    public String listOfTasks(TaskList tasksList, int count) {
        String output = LINE + "\n";
        for (int i = 0; i < count; i++) {
            output += (i + 1) + "." + tasksList.getTask(i) + "\n";
        }
        output += LINE + "\n";
        return output;
    }

    /**
     * Prints the error message from Duke Exception
     *
     * @param e The DukeException error to be printed
     */
    public String errorPrint(DukeException e) {
        return LINE + "\n" + e.getMessage() + "\n" + LINE;
    }

    /**
     * Prints the tasks found when filtered using the specific keyword
     *
     * @param findTasks
     */
    public String printFoundTasks(TaskList findTasks) {
        String output = LINE + "\n";
        output += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < findTasks.getTaskCount(); i++) {
            output += (i + 1) + ". " + findTasks.getTask(i) + "\n";
        }
        output += LINE + "\n";
        return output;
    }

}
