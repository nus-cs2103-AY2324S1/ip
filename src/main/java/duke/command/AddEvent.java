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
     * @param str The input string containing the task description and event time.
     */
    public AddEvent(String str) {
        super(str);
    }

    private static final String EVENT_PATTERN = "\\S.*\\s/from\\s\\d.*\\s/to\\s\\d.*";

    private Task generateTask(TaskList lst, String[] temp) throws DukeException {
        try {
            LocalDate dFrom = LocalDate.parse(temp[1].substring(5).trim());
            LocalDate dTo = LocalDate.parse(temp[2].substring(3).trim());
            Task newTask = lst.addTask(temp[0].trim(), dFrom, dTo);
            assert newTask != null : "Task should not be null";
            return newTask;
        } catch (DateTimeException e) {
            throw new DukeException(
                    "Can you the following pattern to input the time:\n  "
                            + "event <task name> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n");
        }
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
        if (str.isEmpty()) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if(str.matches(EVENT_PATTERN)) {
            String[] temp = str.split(" /");
            Task newTask = generateTask(lst, temp);
            storage.addToFile(newTask);
            return io.addTask(newTask, lst);
        } else {
            throw new DukeException(
                    "Can you follow the following pattern to add a task:\n  "
                            + "event <task name> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n");
        }
    }
}
