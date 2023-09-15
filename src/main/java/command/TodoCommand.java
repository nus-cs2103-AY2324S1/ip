package command;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.TaskList;
import tasks.TodoTask;

/**
 * The `TodoCommand` class represents a command to create a new todo task.
 * When executed, it parses the command, validates it, and adds
 * a new todo task to the task list if the command is valid.
 */
public class TodoCommand extends Command {
    /**
     * Constructs a new `TodoCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public TodoCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "todo" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 2) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.TODO.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.TODO)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.TODO.getValue()
                )
            );
        }
    }


    /**
     * Executes the "todo" command. It parses the command, validates it, and adds a new
     * todo task to the task list if the command is valid.
     *
     * @param taskList The task list to which the todo task is added.
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }
        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        return taskList.addTask(new TodoTask(description));
    }
}
