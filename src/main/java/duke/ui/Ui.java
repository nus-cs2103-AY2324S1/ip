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
    public void greet() {
        System.out.println(MESSAGE);
    }

    /**
     * Prints a message if a task is added to the list
     *
     * @param input the task added to the list of tasks
     * @param count the number of tasks in the list
     */
    public void taskPrint(Task input, int count) {
        System.out.println(LINE + "\n"
                + "Got it. I've added this task" + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list." + "\n" + LINE);
    }

    /**
     * Prints a message if a task is deleted from the list
     *
     * @param input the task deleted from the list of tasks
     * @param count the number of tasks in the list
     */
    public void deletePrint(Task input, int count) {
        System.out.println(LINE + "\n"
                + "Noted. I've removed this task: " + "\n"
                + input + "\n" + "Now you have " + count
                + " tasks in this list." + "\n" + LINE);
    }

    /**
     * Prints a message if a task is marked as done.
     *
     * @param task the task to be marked as done
     */
    public void markDonePrint(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: " + "\n" + task);
        System.out.println(LINE);
    }

    /**
     * Prints a message if a task is unmarked as done.
     *
     * @param task the task to be unmarked as done
     */
    public void unmarkDonePrint(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet: " + "\n" + task);
        System.out.println(LINE);
    }

    /**
     * Prints a message if a task is marked as done.
     */
    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints the list of tasks
     *
     * @param tasksList the list of tasks
     * @param count the amount of tasks in the tasks list
     */
    public void listOfTasks(TaskList tasksList, int count) {
        System.out.println(LINE);
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasksList.getTask(i));
        }
        System.out.println(LINE);
    }

    /**
     * Prints the error message from Duke Exception
     *
     * @param e The DukeException error to be printed
     */
    public void errorPrint(DukeException e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    public void printFoundTasks(TaskList findTasks) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < findTasks.getTaskCount(); i++) {
            System.out.println((i + 1) + ". " + findTasks.getTask(i));
        }
        System.out.println(LINE);
    }

}
