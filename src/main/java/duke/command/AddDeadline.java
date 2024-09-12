package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Command for adding a deadline task.
 * This command parses the input and adds a new deadline task to the task list.
 */
public class AddDeadline extends Command {

    private static final String DEADLINE_PATTERN = "\\S.*\\s/by\\s\\d.*";

    /**
     * Constructs an AddDeadline command with the given input string.
     *
     * @param str The input string containing the task description and deadline.
     */
    public AddDeadline(String str) {
        super(str);
    }

    private Task generateTask(TaskList lst, String[] temp) throws DukeException {
        try {
            LocalDate d = LocalDate.parse(temp[1]);
            Task newTask = lst.addTask(temp[0].trim(), d);
            assert newTask != null : "Task should not be null";
            return newTask;
        } catch (DateTimeException e) {
            throw new DukeException(
                    "Can you follow the following pattern to input the time:\n  "
                            + "deadline <task name> /by <yyyy-mm-dd>\n");
        }
    }
    /**
     * Executes the AddDeadline command.
     * Parses the input string, adds a new deadline task to the task list,
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
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (str.matches(DEADLINE_PATTERN)){
            String[] temp = str.split(" /by ");
            Task newTask = generateTask(lst, temp);
            storage.addToFile(newTask);
            return io.addTask(newTask, lst);
        } else {
            throw new DukeException(
                    "Can you follow the following pattern to add a task:\n  "
                            + "deadline <task name> /by <deadline>\n");
        }
    }
}
