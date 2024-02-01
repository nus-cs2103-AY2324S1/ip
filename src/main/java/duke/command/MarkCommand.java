package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * The MarkCommand class represents a command to mark tasks as complete.
 * It parses the user input and handles exceptions related to invalid input.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a new MarkCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Mark command, marking a task in tasks as done.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     */
    @Override
    public String execute(TaskList tasks , Ui ui, Storage storage) {
        try {
            String[] words = this.fullCommand.split(" ", 2);
            Task t = tasks.getTasks().get(Integer.parseInt(words[1]) - 1);
            t.markDone();
            StringBuilder sb = new StringBuilder();
            sb.append("Nice! I've marked this task as done: \n");
            sb.append(t).append("\n");
            return sb.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("☹ OOPS!!! I'm sorry, please enter a valid index to mark");
        }
    }
}
