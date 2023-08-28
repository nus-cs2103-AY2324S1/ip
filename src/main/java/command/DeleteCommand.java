package command;

import exception.InvalidCommandException;
import exception.MissingArgumentException;
import exception.InvalidInputException;
import parser.CommandParser;
import task.TaskList;
import ui.Reply;

/**
 * Class for Delete command with its static implementation of its processes
 */
public class DeleteCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();

    /**
     * Main process of Delete Command. The choice of keeping the command methods static is because there is no need
     * for multiple instances of commands
     * Validates the input after the delete keyword in the string. Deletes the task if it's a vlid input.
     * Returns to homepage otherwise.
     * @param input String input containing the command and its arguments
     * @throws InvalidInputException if the arguments after command is invalid
     * @throws MissingArgumentException if the arguments after the command is missing
     * @throws InvalidCommandException if the command in the string is invalid
     */
    public static void start(String input) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        String number = CommandParser.getCommandArguments(input);
        try {
            tasks.deleteTask(Integer.parseInt(number));
        } catch (NumberFormatException e ) {
            reply.printDialog(" Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog(" Index number does not exist in our list");
        }
    }
}
