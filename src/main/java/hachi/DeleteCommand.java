package hachi;

import hachi.exceptions.EmptyNumberException;
import hachi.exceptions.HachiException;
import hachi.exceptions.InvalidArgumentException;
import hachi.exceptions.TooManyArgumentsException;

/**
 * Represents the "delete" command.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private TaskList taskList;

    private Storage storage;

    /**
     * Constructor method for the DeleteCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     * @param storage The storage used by the app.
     */
    public DeleteCommand(String[] arguments, TaskList taskList, Storage storage) {
        super(arguments);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        try {
            int i = Parser.parseTaskNumber(getArguments()[0], taskList.size());
            Task t = taskList.remove(i);
            storage.updateTaskFile(taskList);
            return "Noted. I've removed this task:\n   " + t
                    + String.format("\nNow you have %d tasks in the list.", taskList.size());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(DeleteCommand.COMMAND_WORD);
        }
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength > 1) {
            throw new TooManyArgumentsException(DeleteCommand.COMMAND_WORD, 1, argumentLength);
        }
        if (argumentLength < 1) {
            throw new EmptyNumberException(DeleteCommand.COMMAND_WORD);
        }
    }
}
