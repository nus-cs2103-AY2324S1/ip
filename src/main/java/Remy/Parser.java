package remy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import remy.command.Command;
import remy.command.DeadlineCommand;
import remy.command.DeleteCommand;
import remy.command.EventCommand;
import remy.command.ExitCommand;
import remy.command.FindCommand;
import remy.command.ListCommand;
import remy.command.MarkCommand;
import remy.command.TodoCommand;
import remy.command.UnmarkCommand;

/**
 * A class that makes sense of user inputs.
 */
public class Parser {

    /**
     * Parses user input and carries out any follow-up action (e.g. remy.remy.Task.remy.remy.Task creation).
     * Solution below inspired by addressbook Level 2 implementation.
     *
     * @param input
     */
    public static Command parse(String input) throws ChatbotException {
        // Obtains first word of user input, which should state the action to be performed
        String action = input.split(" ")[0].toLowerCase();

        switch (action) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(input);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(input);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(input);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(input);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(input);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(input);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(input);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ChatbotException("No such command lah.");
        }
    }

    /**
     * Returns true if a priority String matches either "high", "medium" or "low".
     *
     * @param input User input that might be a priority.
     */
    public static boolean checkValidPriority(String input) throws ChatbotException {
        if (input == null) {
            return false;
        }

        String formattedInput = input.trim().toLowerCase();
        if (formattedInput.equals("high")
                || formattedInput.equals("medium")
                || formattedInput.equals("low")) {
            return true;
        } else {
            throw new ChatbotException("Your inputted priority is: " + input + "\n"
                    + "Priority can only be 'high', 'medium', or 'low'.");
        }
    }

    /**
     * Checks if a provided input is a date with yyyy-mm-dd format.
     *
     * @param input User input that might be a date.
     */
    public static boolean checkValidDate(String input) throws ChatbotException {
        if (input == null) {
            return false;
        }

        String formattedInput = input.trim();

        // Define the regular expression pattern
        String regex = "\\d{4}-\\d{2}-\\d{2}";

        // Define the regex for the date format yyyy-mm-dd
        Pattern pattern = Pattern.compile(regex);


        // Create a Matcher to check if the input matches the pattern
        Matcher matcher = pattern.matcher(formattedInput);

        if (!matcher.matches()) {
            throw new ChatbotException("Wrong date format lah. Use yyyy-mm-dd.");
        }

        return true;
    }
}
