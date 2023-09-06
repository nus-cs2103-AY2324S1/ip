package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    private static final String BAR = "____________________________________________________________";

    /**
     * Greets the user.
     */
    public static String greetUser() {
        return "Hello! I'm CringeBot\n"
                + "What can I do for you?";
    }

    /**
     * Says goodbye.
     */
    public static String bidFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print the items from the list of task.
     *
     * @param tasks takes in the list of tasks.
     */
    public static String printItems(TaskList tasks) {
        StringBuilder sayWord = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sayWord.append(String.format("\n%d.%s", i + 1, tasks.getTaskWithIndex(i)));
        }
        return sayWord.toString();
    }

    public static String printFound(String foundItems) {
        return String.format("%s%s", "Here are the matching tasks in your list:", foundItems);
    }

    /**
     * Prints after item has been deleted.
     *
     * @param taskSize Size of the list.
     * @param deletedTask Task that has been deleted.
     */
    public static String deleteItem(int taskSize, Task deletedTask) {
        return "Noted. I've removed this task:\n"
                + deletedTask
                + "\nNow you have "
                + taskSize
                + " tasks in the list.";
    }

    /**
     * Wraps the print statement with bars.
     *
     * @param input input to be printed.
     */
    public static void printWrapped(String input) {
        System.out.printf("%s\n%s\n%s%n", Ui.BAR, input, Ui.BAR);
    }
}
