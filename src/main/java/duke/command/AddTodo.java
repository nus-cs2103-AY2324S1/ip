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
     * @param s The input string containing the task description.
     */
    public AddTodo(String s) {
        super(s);
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
        if (s.isEmpty() || s.equals(" ")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task newTask = lst.addTask(s);
            storage.addToFile(newTask);
            return io.addTask(newTask, lst);
        }
    }
}
