package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
public class Parser {
    public static Command parse(String fullCommand) {
        String[] splittedCommand = fullCommand.split(" ");
        if (fullCommand.equals("list")) {
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
        } else if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else {
            return new AddCommand(fullCommand);
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
