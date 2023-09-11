package duke;

import duke.task.Task;
import java.util.Scanner;

public class UiManager {

    static final String LOGO = "   /\\_/\\  \n" +
            "  ( o.o ) \n" +
            "   > ^ <\n";
    static final String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";

    static final String EXIT = "Bye(T.T), Hope to see you again soon!";
    private Scanner sc;

    /**
     * Constructor.
     */
    public UiManager() {

    }

    /**
     * Shows the welcome message.
     */
    public String getWelcomeMessage() {
        return LOGO + "/n" + GREETING + "\n";
    }

    /**
     * Show the goodbye message.
     */
    public String getGoodByeMessage() {
        return EXIT;
    }

    /**
     * Prints the task list with certain format.
     * @param taskList Task list that going to be printed.
     * @throws DukeException If there is a exception when accessing task list.
     */
    public String getListMessage(TaskList taskList) throws DukeException {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Prints the mark message when mark a task.
     * @param task Task that going to be marked.
     */
    public String getMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" +
                "    " + task.toString();
    }

    /**
     * Prints the unmark message when unmark a task.
     * @param task Task that going to be unmarked.
     */
    public String getUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" +
                "    " + task.toString();
    }

    /**
     * Prints the add message when add a task inside the task list.
     * @param task Task that going to be added into task list.
     * @param taskList Task list that stored the task.
     */
    public String getAddTaskMessage(Task task, TaskList taskList) {
        return "Got it, I've added this task:\n    "  +
                task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints the delete message when deleting a task.
     * @param task Task that going to be deleted.
     * @param taskList Task list that stored the task.
     */
    public String getDeleteMessage(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";

    }

    /**
     * Prints the matching task list in certain format.
     * @param taskList Task list that going to be printed.
     * @throws DukeException When there is an exception.
     */
    public String getMatchingList(TaskList taskList) throws DukeException {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
        }
        return result;
    }


    /**
     * Shows erroe message.
     * @param s The error message.
     */
    public String getErrorMessage(String s) {
        return s;
    }

    /**
     * Prints the line.
     */
    public String getsLine() {
        return "_____________________________________________________________________________________";
    }

}
