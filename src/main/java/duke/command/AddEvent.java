package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;


/**
 * Command for adding an event task.
 * This command parses the input and adds a new event task to the task list.
 */
public class AddEvent extends Command {

    /**
     * Constructs an AddEvent command with the given input string.
     *
     * @param s The input string containing the task description and event time.
     */
    public AddEvent(String s) {
        super(s);
    }


    /**
     * Executes the AddEvent command.
     * Parses the input string, adds a new event task to the task list,
     * and updates the user interface and storage accordingly.
     *
     * @param lst The task list to which the new task will be added.
     * @param io The UI handling input and output.
     * @param storage The storage handler for taking and storing task data.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList lst, UI io, Storage storage) throws DukeException {
        if (s.isEmpty() || s.equals(" ")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!s.matches(" \\S.*\\s/from\\s\\d.*\\s/to\\s\\d.*")) {
            throw new DukeException(
                    "OOPS!!! Please follow the following pattern to add a task:\n  "
                            + "deadline <task name> /by <deadline>\n");
        } else {
            String[] temp = s.split(" /");
            try {
                LocalDate dFrom = LocalDate.parse(temp[1].substring(5));
                LocalDate dTo = LocalDate.parse(temp[2].substring(3));
                Task newTask = lst.addTask(temp[0].substring(1), dFrom, dTo);
                storage.addToFile(newTask);
                return io.addTask(newTask, lst);
            } catch (DateTimeException e) {
                throw new DukeException(
                        "OOPS!!! Please follow the following pattern to input the time:\n  "
                                + "event <task name> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n");
            }

        }
    }
}
