package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * The <code>Ui</code> class stores the chatbot replies to user commands, returning the
 * appropriate response to each one.
 */

public class Ui {
    private Scanner sc;
    private static String line = "______________________\n";
    private static String name = "Remi";
    private static String intro = "Greetings. I am " + name + ".\n" + "What can I do for you?\n";
    private static String done = "It is accomplished.\n";
    private static String undone = "It is unfinished.\n";
    private static String addTask = "Another task? Very well.\n";
    private static String removeTask = "It is gone with the wind.\n";
    private static String failure = "A critical failure occurred. Farewell.";
    private static String loadNew = "Here's a blank one instead.";

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
        return line + intro + line;
    }

    /**
     * Returns the failure message.
     *
     * @return Failure
     */
    public String failure() {
        return failure + "\n";
    }

    /**
     * Returns the file loading error.
     *
     * @return File error
     */
    public String showLoadingError() {
        return loadNew + "\n";
    }

    /**
     * Returns the exit statements.
     *
     * @return Exit
     */
    public String exit() {
        return line + "Farewell.\n" + line;
    }

    /**
     * Acknowledges the marking of a task.
     *
     * @return Mark acknowledgement
     */
    public String markText(Task task) {
        return line + done + task.toString() + "\n" + line;
    }

    /**
     * Acknowledges the unmarking of a task.
     *
     * @return Unmark acknowledgement
     */
    public String unmarkText(Task task) {
        return line + undone + task.toString() + "\n" + line;
    }

    /**
     * Acknowledges the addition of a task.
     *
     * @return Task acknowledgement
     */
    public String taskText(Task task, int len) {
        return line + addTask + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + line;
    }

    /**
     * Acknowledges the deletion of a task.
     *
     * @return Delete acknowledgement
     */
    public String removeText(Task task, int len) {
        return line + removeTask + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + line;
    }

    /**
     * Acknowledges the marking of a task.
     *
     * @return Mark acknowledgement
     */
    public String errorMsg(String err) {
        return line + err + "\n" + line;
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
            return line + res + line;
        }
        return line + "You have no tasks for the day. Congratulations?" + "\n" + line;
    }
}
