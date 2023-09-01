package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Todo;

/**
 * The TodoCommand class represents a command to add a Todo task to the task list in the Duke application.
 * It parses the user input and handles exceptions related to invalid input.
 */
public class TodoCommand extends Command {

    /**
     * Constructs a new TodoCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public TodoCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Todo command, adding a new Todo task to the task list.
     *
     * @param tasks   The task list to which the Todo task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     */
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) throws InvalidArgumentException {
        String[] words = this.fullCommand.split(" ", 2);
        if (words.length < 2) {
            throw new InvalidArgumentException("todo");
        } else {
            Todo t = new Todo(words[1]);
            tasks.addTask(t);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
