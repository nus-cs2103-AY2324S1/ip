package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The <code>Ui</code> class stores the chatbot replies to user commands, returning the
 * appropriate response to each one.
 */

public class Ui {
    private Scanner sc;
    private static String LINE = "______________________\n";
    private static String NAME = "Remi";
    private static String INTRO = "Greetings. I am " + NAME + ".\n" + "What can I do for you?\n";
    private static String DONE = "It is accomplished.\n";
    private static String UNDONE = "It is unfinished.\n";
    private static String ADD_TASK = "Another task? Very well.\n";
    private static String REMOVE_TASK = "It is gone with the wind.\n";
    private static String FAILURE = "A critical failure occurred. Farewell.";
    private static String LOAD_NEW = "Here's a blank one instead.";

    /**
     * The class constructor.
     *
     * @param scanner The scanner that the UI uses to read commands.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets the next line of commands from the user.
     *
     * @return Next command
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Returns the startup greeting.
     *
     * @return Greeting
     */
    public String startup() {
        return LINE + INTRO + LINE;
    }

    /**
     * Returns the failure message.
     *
     * @return Failure
     */
    public String failure() {
        return FAILURE + "\n";
    }

    /**
     * Returns the file loading error.
     *
     * @return File error
     */
    public String showLoadingError() {
        return LOAD_NEW + "\n";
    }

    /**
     * Returns the exit statements.
     *
     * @return Exit
     */
    public String exit() {
        return LINE + "Farewell.\n" + LINE;
    }

    /**
     * Acknowledges the marking of a task.
     *
     * @return Mark acknowledgement
     */
    public String markText(Task task) {
        return LINE + DONE + task.toString() + "\n" + LINE;
    }

    /**
     * Acknowledges the unmarking of a task.
     *
     * @return Unmark acknowledgement
     */
    public String unmarkText(Task task) {
        return LINE + UNDONE + task.toString() + "\n" + LINE;
    }

    /**
     * Acknowledges the addition of a task.
     *
     * @return Task acknowledgement
     */
    public String taskText(Task task, int len) {
        return LINE + ADD_TASK + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + LINE;
    }

    /**
     * Acknowledges the deletion of a task.
     *
     * @return Delete acknowledgement
     */
    public String removeText(Task task, int len) {
        return LINE + REMOVE_TASK + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + LINE;
    }

    /**
     * Acknowledges the marking of a task.
     *
     * @return Mark acknowledgement
     */
    public String errorMsg(String err) {
        return LINE + err + "\n" + LINE;
    }

    /**
     * Prints the <code>TaskList</code>
     *
     * @return List of tasks
     */
    public String getList(TaskList list) {
        if (list.getLength() != 0) {
            String res = "Here are your tasks: \n";
            for (int i = 0; i < list.getLength(); i++) {
                String prev = res;
                int count = i + 1;
                res = prev + count + "." + list.getTask(i).toString() + "\n";
            }
            return LINE + res + LINE;
        }
        return LINE + "You have no tasks for the day. Congratulations?" + "\n" + LINE;
    }

    public String getMatchingList(TaskList list, List<Integer> indices) {
        if (indices.size() != 0) {
            String res = "Here are the task(s) matching that description: \n";
            for (int i = 0; i < indices.size(); i++) {
                String prev = res;
                int count = i + 1;
                int index = indices.get(i);
                res = prev + count + "." + list.getTask(index).toString() + "\n";
            }
            return line + res + line;
        }
        return line + "No tasks found. Apologies." + "\n" + line;
    }
}
