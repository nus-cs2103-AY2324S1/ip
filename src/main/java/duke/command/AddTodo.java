package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;



/**
 * Command for adding a todo task.
 * This command parses the input and adds a new todo task to the task list.
 */
public class AddTodo extends Command {

    /**
     * Constructs an AddTodo command with the given input string.
     *
     * @param str The input string containing the task description.
     */
    public AddTodo(String str) {
        super(str);
    }

    private static final String TODO_PATTERN = "\\S.*";

    private Task generateTask(TaskList lst) throws DukeException {
        Task newTask = lst.addTask(str);
        assert newTask != null : "Task should not be null";
        return newTask;
    }

    /**
     * Executes the AddTodo command.
     * Parses the input string, adds a new todo task to the task list,
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
            throw new DukeException("The description of a todo cannot be empty.");
        } else if (str.matches(TODO_PATTERN)) {
            Task newTask = generateTask(lst);
            storage.addToFile(newTask);
            return io.addTask(newTask, lst);
        } else {
            throw new DukeException(
                    "Can you follow the following pattern to add a task:\n  "
                            + "todo <task name>\n");
        }
    }
}
