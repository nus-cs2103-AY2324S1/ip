package Command;

import Exception.InvalidCommandException;
import Exception.MissingArgumentException;
import Exception.InvalidInputException;
import Enums.Command;
import Parser.CommandParser;
import Task.TaskList;
import Ui.Reply;

public class UnmarkCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();
    public static void start(String input) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        String number = CommandParser.getCommandArguments(input, Command.UNMARK);
        try {
            tasks.unmarkDone(Integer.parseInt(number));
        } catch (NumberFormatException e ) {
            reply.printDialog("Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog("Index number does not exist in our list");
        }
    }
}
