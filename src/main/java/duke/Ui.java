package duke;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    private final String chatbotName = "Gobble Gobble";
    public final String lineSeparator = "____________________________________________________________";

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        System.out.println(Duke.lineSeparator + "\n" + "Hello! I'm " + this.chatbotName + "\n" +
                "What can I do for you?" + "\n" + Duke.lineSeparator);
    }

    /**
     * Prints the goodbye message.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message.
     *
     * @param message error message.
     */
    public void showLoadingError(String message) {
        System.out.println(lineSeparator + "\n" + message + "\n" + lineSeparator);
    }

    /**
     * Prints the task as marked.
     *
     * @param task task to be marked.
     */

    public void mark(Task task) {
        System.out.println(Duke.lineSeparator + "\n" + "Nice! I've marked this task as done:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    /**
     * Prints the task as unmarked.
     *
     * @param task task to be unmarked.
     */

    public void unmark(Task task) {
        System.out.println(Duke.lineSeparator + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                task + "\n" + Duke.lineSeparator);
    }

    /**
     * Prints the task as deleted.
     *
     * @param task task to be deleted.
     * @param size size of the task list after deletion.
     */
    public void delete(Task task, int size) {
        System.out.println(Duke.lineSeparator + "\n" + "Noted. I've removed this task:" + "\n"
                + task.getDescription() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }

    /**
     * Prints the task as added.
     *
     * @param task task to be added.
     * @param size size of the task list after addition.
     */
    public void addTask(Task task, int size) {
        System.out.println(Duke.lineSeparator + "\n" + "Got it. I've added this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + Duke.lineSeparator);
    }

    /**
     * Prints the task list.
     *
     * @param taskList list of tasks.
     */
    public void printList(TaskList taskList) {
        System.out.println(Duke.lineSeparator);
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }
}
