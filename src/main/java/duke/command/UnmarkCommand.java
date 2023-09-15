package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * The UnmarkCommand class represents a command to unmark tasks.
 * It parses the user input and handles exceptions related to invalid input.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs a new UnmarkCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public UnmarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Unmark command, marking a task in tasks as not done.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     */
    @Override
    public String execute(TaskList tasks , Ui ui, Storage storage) throws InvalidArgumentException {
        try {
            String[] words = this.fullCommand.split(" ", 2);
            Task t = tasks.getTasks().get(Integer.parseInt(words[1]) - 1);
            t.markUndone();
            StringBuilder sb = new StringBuilder();
            sb.append("OK, I've marked this task as not done yet: \n");
            sb.append(t).append("\n");
            return sb.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("☹ OOPS!!! I'm sorry, please enter a valid index to mark");
        }
    }
}
