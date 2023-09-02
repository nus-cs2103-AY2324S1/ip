package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

/**
 * Parses inputs from the user.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class Parser {

    /**
     * Parses the input into a command.
     *
     * @param input the input that will be parsed.
     * @return A command representing the user input.
     */
    public static Command parse(String input) {
        String[] splittedCommand = input.split(" ");
        if (input.equals("list")) {
            return new ListCommand();
        } else if (splittedCommand[0].equalsIgnoreCase("mark") && splittedCommand.length == 2 &&
                isInteger((splittedCommand[1]))) {
            return new MarkCommand(true, Integer.parseInt(splittedCommand[1]) - 1);
        } else if (splittedCommand[0].equalsIgnoreCase("unmark") && splittedCommand.length == 2 &&
                isInteger((splittedCommand[1]))) {
            return new MarkCommand(false, Integer.parseInt(splittedCommand[1]) - 1);
        } else if (splittedCommand[0].equalsIgnoreCase("delete") && splittedCommand.length == 2 &&
                isInteger((splittedCommand[1]))) {
            return new DeleteCommand(Integer.parseInt(splittedCommand[1]) - 1);
        } else if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (splittedCommand[0].equalsIgnoreCase("find")) {
            return new FindCommand(input.substring(5));
        } else {
            return new AddCommand(input);
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer check = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
