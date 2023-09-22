package hachi;

import hachi.exceptions.EmptyNumberException;
import hachi.exceptions.HachiException;
import hachi.exceptions.InvalidArgumentException;
import hachi.exceptions.TooManyArgumentsException;

/**
 * Represents the "unmark" command.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private TaskList taskList;

    private Storage storage;

    /**
     * Constructor method for the UnmarkCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     * @param storage The storage used by the app.
     */
    public UnmarkCommand(String[] arguments, TaskList taskList, Storage storage) {
        super(arguments);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        try {
            int i = Parser.parseTaskNumber(getArguments()[0], taskList.size());
            Task t = taskList.unmark(i);
            storage.updateTaskFile(taskList);
            return "OK, I've marked this task as not done yet:\n   " + t;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(MarkCommand.COMMAND_WORD);
        }
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength > 1) {
            throw new TooManyArgumentsException(COMMAND_WORD, 0, argumentLength);
        }
        if (argumentLength < 1) {
            throw new EmptyNumberException(MarkCommand.COMMAND_WORD);
        }
    }
}
