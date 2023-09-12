package cringebot.ui;

import cringebot.tasks.Task;
import cringebot.tasks.TaskList;

/**
 * Class that handles the statements said by CringeBot
 */
public class Ui {

    /**
     * Greets the user.
     *
     * @return the greeting.
     */
    public static String greetUser() {
        return "Hello! I'm CringeBot\nWhat can I do for you?";
    }

    /**
     * Says goodbye.
     *
     * @return the farewell message.
     */
    public static String bidFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Print the items from the list of task.
     *
     * @param tasks takes in the list of tasks.
     * @return items from tasks.
     */
    public static String printItems(TaskList tasks) {
        StringBuilder sayWord = new StringBuilder("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            sayWord.append(String.format("\n%d.%s", i + 1, tasks.getTaskWithIndex(i)));
        }
        return sayWord.toString();
    }

    /**
     * Prints after item has been deleted.
     *
     * @param taskSize Size of the list.
     * @param deletedTask Task that has been deleted.
     * @return item that was deleted
     */
    public static String deleteItem(int taskSize, Task deletedTask) {
        return String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                deletedTask,
                taskSize
        );
    }
}
