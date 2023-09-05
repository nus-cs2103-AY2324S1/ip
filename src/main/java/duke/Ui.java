package duke;

import duke.Exception.DukeException;
import duke.task.Task;

/**
 * The class that handles the UI aspects of the chatbot like the messages
 * that will be printed.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("\t " + "Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n" +
                "\t " + "What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodByeMessage() {
        printHorizontalLine();
        System.out.println("\t " + "Bye! Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints the task as being marked as done.
     * @param index the index of the task that is to be marked.
     * @param tasks the TaskList being used.
     */
    public void printMarkTasksAsDone(int index, TaskList tasks) {
        printHorizontalLine();
        System.out.println("\tGreat! I've marked this task as done:");
        System.out.println("\t" + index + "." + tasks.getTask(index - 1).toString());
        printHorizontalLine();

    }

    /**
     * Prints the task as being marked as not done yet.
     * @param index the index of the task is to be marked as not done.
     * @param tasks the TaskList being used.
     */

    public void printMarkTasksAsNotDone(int index, TaskList tasks) {
        printHorizontalLine();
        System.out.println("\tOk! I've marked this task as not done yet:");
        System.out.println("\t" + index + "." + tasks.getTask(index - 1).toString());
        printHorizontalLine();

    }

    /**
     * Prints the TaskList.
     * @param tasks the TaskList being used.
     * @throws DukeException
     */
    public void printListMessage(TaskList tasks) throws DukeException {
            printHorizontalLine();
            tasks.printTasks();
            printHorizontalLine();
    }

    /**
     * Prints message that the task is added.
     */

    public void printAddedTask() {
        System.out.println("\tNo problem! I have added this task:");
    }

    /**
     * Prints the horizontal line.
     */
    public void printHorizontalLine() {
        System.out.println("    __________________________________________________________________");
    }

    /**
     * Prints that a task has been deleted.
     * @param pos the index of the element.
     * @param tasks the TaskList being used.
     * @param element the Task that is to be deleted.
     * @throws DukeException
     */
    public void printDeleteTasks(int pos, TaskList tasks, Task element) throws DukeException {
        printHorizontalLine();
        System.out.println("\tOkie I've removed this task:\n\t" + element.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Prints that a task has been added.
     * @param tasks the TaskList being used.
     * @param task the task that is to be added.
     */

    public void printAddTaskToList(TaskList tasks, Task task) {
        printHorizontalLine();
        printAddedTask();
        System.out.println("\t" + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list");
        printHorizontalLine();
    }


}
