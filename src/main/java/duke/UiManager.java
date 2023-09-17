package duke;

import duke.task.Task;
import java.util.Scanner;

public class UiManager {

    static final String LOGO = "    /\\_/\\  \n" +
            "   ( o.o ) \n" +
            "   > ^ <\n";
    static final String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";

    static final String EXIT = "Bye(T.T), Hope to see you again soon!";
    private Scanner sc;

    /**
     * Constructs UI manager.
     */
    public UiManager() {

    }

    /**
     * Returns welcome message as a string.
     *
     * @return Welcome message as a string.
     */

    public static String getWelcomeMessage() {
        return LOGO + "\n" + GREETING + "\n";
    }

    /**
     * Returns goodbye message as a string.
     *
     * @return Goodbye message as a strig.
     */

    public String getGoodByeMessage() {
        return EXIT;
    }

    /**
     * Returns task list as a string format.
     *
     * @param taskList The task list going to be returned as a string.
     * @return task list as a string format.
     * @throws DukeException When the index out of the bounds.
     */
    public String getListMessage(TaskList taskList) throws DukeException {
        assert taskList != null : "TaskList should not be null";
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Returns the message in string format when user mark a task.
     *
     * @param task The task going to be marked.
     * @return The message in string format when user mark a task.
     */
    public String getMarkMessage(Task task) {
        assert task != null: "Task should not be null";
        return "Nice! I've marked this task as done:\n" +
                "    " + task.toString();
    }

    /**
     * Returns the unmark message in string format when user unmark a task.
     *
     * @param task The task going to be unmarked.
     * @return The message in string format when user unmark a task
     */
    public String getUnmarkMessage(Task task) {
        assert task != null: "Task should not be null";
        return "OK, I've marked this task as not done yet:\n" +
                "    " + task.toString();
    }

    /**
     * Returns the message in string format when user add a task into task list.
     *
     * @param task The task going to be added.
     * @param taskList The task list which stored tasks.
     * @return The message in string format when user add a task into task list.
     */
    public String getAddTaskMessage(Task task, TaskList taskList) {
        assert task != null: "Task should not be null";
        assert taskList != null: "Task list should not be null";
        return "Got it, I've added this task:\n    "  +
                task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Returns the message in string format when user delete a task.
     *
     * @param task The task going to be deleted.
     * @param taskList The task list which stored tasks.
     * @return The message in string format when user delete a task.
     */
    public String getDeleteMessage(Task task, TaskList taskList) {
        assert task != null: "Task should not be null";
        return "Noted. I've removed this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";

    }

    /**
     * Returns the task list as another string format.
     *
     * @param taskList The task list going to be converted to string.
     * @return The task list as another string format.
     * @throws DukeException If there is a index out of bounds.
     */
    public String getMatchingList(TaskList taskList) throws DukeException {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
        }
        return result;
    }

}
