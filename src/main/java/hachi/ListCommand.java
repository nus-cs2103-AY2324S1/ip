package hachi;

import exceptions.HachiException;
import exceptions.TooManyArgumentsException;

/**
 * Represents the "list" command.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    private TaskList taskList;

    /**
     * Constructor method for the ListCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     */
    public ListCommand(String[] arguments, TaskList taskList) {
        super(arguments);
        this.taskList = taskList;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        return taskList.toString();
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength > 0) {
            throw new TooManyArgumentsException(COMMAND_WORD, 0, argumentLength);
        }
    }
}
