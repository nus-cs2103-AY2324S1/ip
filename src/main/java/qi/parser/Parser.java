package qi.parser;

import qi.command.*;
import qi.qiexception.QiException;

import java.time.LocalDate;

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
        if (command.equals("bye")) {
            return new ExitCommand();
        }

        if (command.equals("list")) {
            return new ListCommand();
        }

        if (command.startsWith("mark")) {
            try {
                int taskId = Integer.parseInt(command.substring(5));
                return new MarkCommand(taskId);
            } catch (StringIndexOutOfBoundsException e) {
                throw new QiException("☹ OOPS!!! Please specify which task you want to mark.");
            }
        }

        if (command.startsWith("unmark")) {
            try {
                int taskId = Integer.parseInt(command.substring(7));
                return new UnmarkCommand(taskId);
            } catch (StringIndexOutOfBoundsException e) {
                throw new QiException("☹ OOPS!!! Please specify which task you want to unmark.");
            }
        }

        if (command.startsWith("todo")) {
            try {
                String task = command.substring(5);
                return new AddCommand(task);
            } catch (StringIndexOutOfBoundsException e) {
                throw new QiException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        }

        if (command.startsWith("deadline")) {
            try {
                int idx = 9;
                while (idx < command.length() && command.charAt(idx) != '/') {
                    idx++;
                }
                String task = command.substring(9, idx - 1);
                String deadline = command.substring(idx + 4);

                return new AddCommand(task, LocalDate.parse(deadline));
            } catch (StringIndexOutOfBoundsException e) {
                throw new QiException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        }

        if (command.startsWith("event")) {
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
                throw new QiException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        }

        if (command.startsWith("delete")) {
            try {
                int taskId = Integer.parseInt(command.substring(7));
                return new DeleteCommand(taskId);
            } catch (StringIndexOutOfBoundsException e) {
                throw new QiException("☹ OOPS!!! Please specify which task you want to delete.");
            }
        }

        return new InvalidCommand();
    }
}
