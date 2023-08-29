package command;

import exception.InvalidCommandException;
import exception.InvalidInputException;
import exception.MissingArgumentException;
import parser.CommandParser;
import task.TaskList;
import ui.Reply;

/**
 * Class for Fnd command with its static implementation of its processes
 */
public class FindCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();

    /**
     * Main process of Mark Command.
     * Validates the input after the find keyword in the string and finds the task
     * Returns to homepage otherwise.
     * @param input String input containing the command and its arguments
     * @throws MissingArgumentException if the arguments after the command is missing
     * @throws InvalidCommandException if the command in the string is invalid
     * @throws InvalidInputException if there is more than one keyword in the input
     */
    public static void start(String input) throws
            InvalidCommandException,
            MissingArgumentException,
            InvalidInputException {

        String argument = CommandParser.getCommandArguments(input);

        String[] split = argument.split(" ");
        if (split.length > 1) {
            throw new InvalidInputException();
        }

        tasks.findTask(argument);
    }
}
