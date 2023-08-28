package command;

import exception.InvalidCommandException;
import exception.MissingArgumentException;
import exception.InvalidInputException;
import enums.Command;
import parser.CommandParser;
import task.TaskList;
import ui.Reply;

public class MarkCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();
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
