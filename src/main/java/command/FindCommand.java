package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

/**
 * The `FindCommand` class represents a command to search for tasks based on a keyword in the Duke application.
 */
public class FindCommand extends Command {
    private final boolean valid;
    private String keyword;

    /**
     * Constructs a `FindCommand` with the given raw command.
     *
     * @param rawCommand The raw command input by the user.
     */
    public FindCommand(String rawCommand) {
        super(rawCommand);
        this.valid = validate(rawCommand);
    }

    /**
     * Validates the `FindCommand` based on the raw command input.
     *
     * @param rawCommand The raw command input by the user.
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.FIND);
    }

    private void deconstruct(String rawCommand) {
        if (!this.valid) {
            return;
        }
        this.keyword = Parser.getArgs(rawCommand)[1];
    }

    /**
     * Executes the `FindCommand` to search for tasks based on the specified keyword.
     *
     * @param taskList The task list in which to search for tasks.
     */
    public void execute(TaskList taskList) {
        if (!this.valid) {
            return;
        }
        this.deconstruct(super.getRawCommand());
        taskList.findTask(this.keyword);
    }
}
