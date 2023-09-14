package hachi;

import exceptions.HachiException;
import exceptions.InvalidArgumentException;
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
        String[] arguments = getArguments();
        checkArgumentLength(arguments.length);
        if (!arguments[0].isEmpty() && !arguments[0].equals("sort")) {
            throw new InvalidArgumentException(COMMAND_WORD);
        } else if (!arguments[1].isEmpty() && arguments[1].equals("name")) {
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
