package Command;

import Exception.InvalidCommandException;
import Exception.MissingArgumentException;
import Exception.InvalidInputException;
import Parser.CommandParser;
import Task.TaskList;
import Ui.Reply;

public class DeleteCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();
    public static void start(String input) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        String number = CommandParser.getCommandArguments(input, Enums.Command.DELETE);
        try {
            tasks.deleteTask(Integer.parseInt(number));
        } catch (NumberFormatException e ) {
            reply.printDialog(" Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog(" Index number does not exist in our list");
        }
    }
}
