package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

/**
 * The `ByeCommand` class represents a command to exit the application.
 * When executed, it displays a bye message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new `ByeCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public ByeCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "bye" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.BYE);
    }

    /**
     * Executes the "bye" command. It shows the bye message to the user.
     *
     * @param taskList The task list (not used in this command).
     */
    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        Ui.showByeUser();
    }
}
