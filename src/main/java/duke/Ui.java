package duke;

import duke.task.Task;

/**
 * The Ui class is responsible for user interaction and displaying messages to the user.
 */
public class Ui{
    String name = "Harry Potter";
    String question = "Introducing the Wizarding World Organizer: Your Trusted Guide" +
            " to Efficient & Effective Magical Planning";
    String hello = "Hello muggle! I'm " + name + "\n" + question;
    String bye = "\t" + "Expelliarmus! Hope to see you again muggle! :D";

    /**
     * Default constructor to initialize the Ui class.
     */
    public Ui() {
    }

    /**
     * Displays a welcome message to the user.
     */
    public void printHello() {
        System.out.println(hello);
    }

    /**
     * Displays a goodbye essage to the user.
     */
    public void printBye() {
        System.out.println(bye);
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param done The task that was marked as done.
     */
    public void printDone(Task done) {
        System.out.println("\t" + "Nice! I've marked this task " +
                "as done:" + "\n" +
                "\t " + done.taskString());
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param notDone The task that was marked as not done.
     */
    public void printNotDone(Task notDone) {
        System.out.println("\t" + "OK, I've marked this task " +
                "as not done yet:" + "\n" + "\t" + " " +
                notDone.taskString());
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param toBeDeleted The task that was deleted.
     * @param tasks       The task list.
     */
    public void printDelete(Task toBeDeleted, TaskList tasks) {
        System.out.println("\tNoted. I've removed this task:\n\t " + toBeDeleted.taskString()
                + "\n\tNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints a message when a task is added to the list.
     *
     * @param task  The task that was added.
     * @param tasks The task list.
     */
    public void printAddTask(Task task, TaskList tasks) {
        int len = tasks.getSize();
        String output = "\tGot it. I've added this task:\n\t\t"
                + task.taskString();
        String listLength = len == 1 ? "Now you have " + len + " task in the list." :
                "Now you have " + len + " tasks in the list.";
        System.out.println(output
                + "\n\t" + listLength);
    }


    /**
     * Prints a custom message for an exception.
     *
     * @param message The custom exception message.
     */
    public void printException(String message) {
        System.out.println(message);
    }

    /**
     * Prints a default exception message for EventDateTime Exception.
     */
    public void printException() {
        System.out.println("Accio error! I don't understand what the input means D:" +
                " Please input a valid date in the format yyyy-MM-dd HHmm " +
                "(the time in the 24-hour format).");
    }

    /**
     * Lists the tasks in the task list.
     *
     * @param tasks The task list to be printed.
     */
    public void listTasks(TaskList tasks) {
        int i = 1;
        System.out.print("\tHere are the tasks in your list:");
        for (int j = 0; j < tasks.getSize(); j++) {
            System.out.println();
            System.out.print("\t" + i + "." + tasks.getTask(j).taskString());
            i++;
        }
        System.out.println();
    }

    public void printMatchingTasks(TaskList tasks){
        int i = 1;
        System.out.print("\tHere are the tasks in your list:");
        for (int j = 0; j < tasks.getSize(); j++) {
            System.out.println();
            System.out.print("\t" + i + "." + tasks.getTask(j).taskString());
            i++;
        }
        System.out.println();
    }
}
