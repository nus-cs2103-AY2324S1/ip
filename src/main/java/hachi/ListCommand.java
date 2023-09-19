package hachi;

import hachi.exceptions.HachiException;
import hachi.exceptions.InvalidArgumentException;
import hachi.exceptions.TooManyArgumentsException;

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
        String[] arguments = getArguments();
        checkArgumentLength(arguments.length);
        if (arguments.length > 0 && !arguments[0].equals("sort")) {
            throw new InvalidArgumentException(COMMAND_WORD);
        } else if (arguments.length > 1 && arguments[1].equals("name")) {
            taskList.sort((x, y) -> x.compareName(y));
        }
        return taskList.toString();
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength > 2) {
            throw new TooManyArgumentsException(COMMAND_WORD, 0, argumentLength);
        }
    }
}
