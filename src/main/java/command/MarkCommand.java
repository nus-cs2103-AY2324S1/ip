package command;

import exception.InvalidCommandException;
import exception.MissingArgumentException;
import exception.InvalidInputException;
import enums.Command;
import parser.CommandParser;
import task.TaskList;
import ui.Reply;

/**
 * Class for Mark command with its static implementation of its processes
 */
public class MarkCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();

    /**
     * Main process of Mark Command. The choice of keeping the command methods static is because there is no need
     * for multiple instances of commands
     * Validates the input after the mark keyword in the string. Marks the task if it's a valid input.
     * Returns to homepage otherwise.
     * @param input String input containing the command and its arguments
     * @throws MissingArgumentException if the arguments after the command is missing
     * @throws InvalidCommandException if the command in the string is invalid
     */
    public static void start(String input) throws
            MissingArgumentException,
            InvalidCommandException {

        String number = CommandParser.getCommandArguments(input);
        try {
            tasks.markDone(Integer.parseInt(number));
        } catch (NumberFormatException e ) {
            reply.printDialog("Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog("Index number does not exist in our list");
        }
    }
}
