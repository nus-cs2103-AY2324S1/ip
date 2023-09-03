package duke.helper;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.DukeException;

/**
 * Parser class that helps to process the commands input to by the user
 */
public class Parser {
    /**
     * scan field to query for the next keyword from user
     */
    private static Scanner scan;

    /**
     * normal constructor for the Parser class
     */
    public Parser() {
        this.scan = new Scanner(System.in);
    }

    /**
     * @param command takes in the first word given by the user
     * @return Command returns the command that would be executed later
     * @throws DukeException throws DukeException dependent on which command is parsed in
     */
    public static Command parse(String command) throws DukeException {
        try {
            Parser.getCommand(command);
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();

            } else if (command.startsWith("mark")) {
                String tasknum = command.split(" ")[1];
                return new MarkCommand(Integer.parseInt(tasknum));

            } else if (command.startsWith("unmark")) {
                String tasknum = command.split(" ")[1];
                return new UnmarkCommand(Integer.parseInt(tasknum));

            } else if (command.startsWith("delete")) {
                String tasknum = command.split(" ")[1];
                return new DeleteCommand(Integer.parseInt(tasknum));
            } else if (command.startsWith("find")) {
                String keyword = command.split(" ")[1];
                return new FindCommand(keyword);
            } else {
                if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                    return new AddCommand(command);
                }
                // no proper keyword was given
            }

        } catch (DukeException e) {
            throw e;
        }
        return null;
    }

    /**
     * Checks if the command is valid by comparing a list of keywords
     * @param command command parsed in by user
     * @return boolean that indicates if the command is valid or not
     * @throws DukeException indicates that the command is invalid if exception is thrown
     */

    static boolean getCommand(String command) throws DukeException {
        String firstword = command.split(" ")[0];
        String[] commands = {"bye", "list", "unmark", "mark", "todo", "deadline", "event", "delete", "find"};
        for (String c: commands) {
            if (c.equals(firstword)) {
                return true;
            }
        }
        throw new DukeException(" Invalid keyword! ");
    }
}
