package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Each user input created into an executable Command
 */
public abstract class Command {
    protected TaskList taskList;

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks user inputs, if invalid throws DukeException
     *
     * @param str  User input split by each word
     * @param task type of task - todo,event,deadline,mark/unmark
     * @throws DukeException
     */
    public static void inputChecker(String[] str, String task) throws DukeException {

        if (str.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    /**
     * receives string of date and time, returns Date object
     *
     * @param str
     * @return Date
     * @throws DukeException
     */
    public static Date dateParser(String str) throws DukeException {
        if (str.length() < 15) {
            throw new DukeException("Invalid date, must be of the form dd/mm/yyyy hhmm");
        }
        String newStr = str.substring(0, 13) + ":" + str.substring(13);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date d1 = df.parse(newStr);
            return d1;
        } catch (Exception e) {
            throw new DukeException("Invalid date");
        }
    }

    abstract public String execute() throws DukeException;
}
