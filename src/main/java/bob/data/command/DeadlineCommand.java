package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.parser.Parser;

/**
 * Add a DeadlineTask into the list.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Creates a DeadlineCommand that creates a DeadlineTask given the specified input.
     * @param input The user input with the details of the DeadlineTask.
     */
    public DeadlineCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(TaskList list) throws DukeException {
        return list.addTaskWithCommand(Parser.CommandType.DEADLINE, this.input);
    }
}
