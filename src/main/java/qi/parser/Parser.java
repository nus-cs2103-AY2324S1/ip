package qi.parser;

import java.time.LocalDate;

import qi.command.AddCommand;
import qi.command.Command;
import qi.command.DeleteCommand;
import qi.command.ExitCommand;
import qi.command.FindCommand;
import qi.command.HelpCommand;
import qi.command.InvalidCommand;
import qi.command.ListCommand;
import qi.command.MarkCommand;
import qi.command.UnmarkCommand;
import qi.qiexception.QiException;

/**
 * Processes string input from users
 */
public class Parser {

    /**
     * Returns a suitable command to execute the input of users in
     * the form of string.
     *
     * @param command String representation of the user command.
     * @return Command to be executed
     * @throws QiException If the user input does not match the required format.
     */
    public static Command parse(String command) throws QiException {
        int idx = command.indexOf(' ');
        String commandPrefix = idx < 0 ? command : command.substring(0, idx);

        switch (commandPrefix) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "mark":
            return parseMark(command);
        case "unmark":
            return parseUnmark(command);
        case "todo":
            return parseTodo(command);
        case "deadline":
            return parseDeadline(command);
        case "event":
            return parseEvent(command);
        case "delete":
            return parseDelete(command);
        case "find":
            return parseFind(command);
        default:
            return new InvalidCommand();
        }
    }

    private static MarkCommand parseMark(String command) throws QiException {
        try {
            int taskId = Integer.parseInt(command.substring(5));
            return new MarkCommand(taskId);
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! Please specify which task you want to mark!");
        }
    }

    private static UnmarkCommand parseUnmark(String command) throws QiException {
        try {
            int taskId = Integer.parseInt(command.substring(7));
            return new UnmarkCommand(taskId);
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! Please specify which task you want to unmark!");
        }
    }

    private static AddCommand parseTodo(String command) throws QiException {
        try {
            String task = command.substring(5);
            return new AddCommand(task);
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! The description of a todo cannot be empty!");
        }
    }

    private static AddCommand parseDeadline(String command) throws QiException {
        try {
            int idx = 9;
            while (idx < command.length() && command.charAt(idx) != '/') {
                idx++;
            }
            String task = command.substring(9, idx - 1);
            String deadline = command.substring(idx + 4);

            return new AddCommand(task, LocalDate.parse(deadline));
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! The description of a deadline cannot be empty!");
        }
    }

    private static AddCommand parseEvent(String command) throws QiException {
        try {
            // find the first slash
            int idx1 = 6;
            while (idx1 < command.length() && command.charAt(idx1) != '/') {
                idx1++;
            }

            // find the second slash
            int idx2 = idx1 + 1;
            while (idx2 < command.length() && command.charAt(idx2) != '/') {
                idx2++;
            }

            String task = command.substring(6, idx1 - 1);
            String startTime = command.substring(idx1 + 6, idx2 - 1);
            String endTime = command.substring(idx2 + 4);
            return new AddCommand(task, startTime, endTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! The description of an event cannot be empty!");
        }
    }

    private static DeleteCommand parseDelete(String command) throws QiException {
        try {
            int taskId = Integer.parseInt(command.substring(7));
            return new DeleteCommand(taskId);
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! Please specify which task you want to delete!");
        }
    }

    private static FindCommand parseFind(String command) throws QiException {
        try {
            String keyWord = command.substring(5);
            return new FindCommand(keyWord);
        } catch (StringIndexOutOfBoundsException e) {
            throw new QiException("OOPS!!! Please specify the keyword you want to find!");
        }
    }
}




