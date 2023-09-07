package duke.ui;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the client facing UI to handle inputs and displays.
 */
public class Ui {
    /**
     * Adds task to TaskList and displays added task info to user.
     * @param tasks TaskList
     * @param task Task to be added
     */
    public String addTask(TaskList tasks, Task task) {
        String res = "";
        res += "Got it. I've added this task:\n";
        res += String.format("  %s\n", task.toString());
        res += String.format("Now you have %d tasks in the list.", tasks.size());
        return res;
    }

    /**
     * Displays bye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays all tasks from TaskList that exist on specified date.
     * @param tasks TaskList
     * @param date Date given by user
     * @throws DukeException if invalid date
     */
    public String date(TaskList tasks, String date) throws DukeException {
        String dmy = Parser.convertToDmy(date);
        if (tasks.isEmpty()) {
            return String.format("There is no task on %s.\n", dmy);
        }
        String res = "";
        res += String.format("Here are the tasks on %s:\n", dmy);
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * Removes task from TaskList and displays removed task info to user.
     * @param tasks TaskList
     * @param task task to be removed
     */
    public String deleteTask(TaskList tasks, Task task) {
        String res = "";
        res += "Noted. I've removed this task:\n";
        res += String.format("  %s\n", task.toString());
        res += String.format("Now you have %d tasks in the list.", tasks.size());
        return res;
    }

    /**
     * Displays all tasks in TaskList that matches specified keyword.
     * @param tasks TaskList
     * @param keyword String of specified keyword
     */
    public String find(TaskList tasks, String keyword) {
        if (tasks.isEmpty()) {
            return "There is no matching task with: " + keyword + "\n";
        }
        String res = "";
        res += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * Displays hello message.
     */
    public String hello() {
        return "Hello! I'm AdaBot.\nWhat do you want to do today?";
    }

    /**
     * Displays all tasks in TaskList.
     * @param tasks TaskList
     */
    public String list(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There is no task in your list.";
        }
        String res = "";
        res += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }
        return res.substring(0, res.length() - 1);
    }

    /**
     * Marks specified task as done.
     * @param tasks TaskList
     * @param task task to be marked
     */
    public String mark(TaskList tasks, Task task) {
        String res = "";
        res += "Nice! I've marked this task as done:\n";
        res += "  " + task.toString();
        return res;
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void printError(String s) {
        System.out.println("OOPS!!! " + s);
    }

    /**
     * Marks specified task as undone.
     * @param tasks TaskList
     * @param task task to be unmarked
     */
    public String unmark(TaskList tasks, Task task) {
        String res = "";
        res += "OK, I've marked this task as not done yet:\n";
        res += "  " + task.toString();
        return res;
    }
}
