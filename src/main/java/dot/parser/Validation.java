package dot.parser;

import dot.errors.DotException;
import dot.errors.TaskError;

/**
 * Contains most of the validation logic of the app.
 */
public class Validation {

    /**
     * Checks whether given input is in date format: dd/MM/yyyy.
     *
     * @param dateInput This is the user input.
     * @return true if input is of valid date format, else false.
     */
    public static boolean isValidDate(String dateInput) {
        String dateRegex = "^([1-9]|0[1-9]|[1-2][0-9]|3[0-1])/([1-9]|0[1-9]|1[0-2])/[0-9]{4}$";

        return dateInput.matches(dateRegex);
    }

    /**
     * Checks whether given input has a valid command.
     *
     * @param input   This is the user input.
     * @param command This is the command we want to check against.
     * @return true if input begins with command.
     */
    public static boolean isValidCommand(String input, String command) {
        assert !command.isEmpty() : "command is supposed to be a string of at least length 1";
        int commandLen = command.length();
        return input.startsWith(command) && (
                input.length() == commandLen || input.charAt(commandLen) == ' ');
    }

    /**
     * Validates whether input is in format: <code>{@literal <command> <integer>.}</code>
     * Assumes valid command.
     *
     * @param input          from the user
     * @param potentialError the TaskError that will handle potential exceptions
     * @return the argument <code>{@literal <integer>}</code>
     * @throws DotException if input is invalid
     */
    public static int getIntIfValidCommandSpaceNumber(String input,
                                                      TaskError potentialError) throws DotException {
        assert potentialError != null : "potentialError is supposed to be nonnull";
        // If position is returned, this value will definitely be overridden
        int position = 1;

        String[] substrings = input.split(" ");
        if (substrings.length == 2) {
            try {
                position = Integer.parseInt(substrings[1]);
            } catch (NumberFormatException e) {
                throw new DotException("Invalid number given", potentialError);
            }
        } else if (substrings.length == 1) {
            throw new DotException("No task number stated", potentialError);
        } else {
            throw new DotException("Too many parameters", potentialError);
        }

        return position;
    }

    /**
     * Validates whether input is in format: <code>{@literal <command> <description>.}</code>
     * We require command parameter because we are not relying on String::split
     * Assumes that command is valid
     *
     * @param input          from user
     * @param command        in format
     * @param parameterDesc  for error message
     * @param potentialError the TaskError that will handle potential exceptions
     * @return the argument <code>{@literal <description>}</code>
     * @throws DotException if input is invalid
     */
    public static String getDescIfValidCommandSpaceDesc(String input, String command, String parameterDesc,
                                                        TaskError potentialError) throws DotException {
        assert potentialError != null : "potentialError is supposed to be nonnull";
        assert !command.isEmpty() : "command is supposed to be a string of at least length 1";
        int commandLen = command.length();
        if (input.strip().length() <= commandLen) {
            throw new DotException(String.format("No %s given", parameterDesc),
                    potentialError);
        }
        return input.substring(commandLen + 1);
    }

    /**
     * Validates whether input is in format: <code>{@literal deadline <desc> /by <deadline>}</code>
     * This is capable of spotting format errors such as overflowing parameters
     *
     * @param input from user
     * @return { description, deadline } if valid
     * @throws DotException if input is invalid
     */
    public static String[] getArgsIfValidDeadlineFormat(String input) throws DotException {
        if (input.length() <= 9) {
            throw new DotException("No task description given", TaskError.ERR_USING_DEADLINE);
        }
        int indexOfSlash = input.indexOf(" /by"); // Case: "deadline /by"
        if (indexOfSlash == -1 || indexOfSlash == 8) {
            throw new DotException("No deadline given or is given without task description.",
                    TaskError.ERR_USING_DEADLINE);
        }
        assert input.matches("deadline .* /by.*");
        // We can assume that input is now in the format "deadline .+ /by.*'
        String[] substrings = input.split(" /by");

        if (substrings.length == 1) {
            throw new DotException("No deadline description given.", TaskError.ERR_USING_DEADLINE);
        } else if (substrings.length != 2) {
            throw new DotException("Too many instances of deadline descriptions.",
                    TaskError.ERR_USING_DEADLINE);
        }
        // Since supposedly filled spaces can appear as whitespace, run check after split
        // We will truncate "deadline" from the first element and strip it
        String description = substrings[0].substring(8).strip();
        if (description.isEmpty()) {
            throw new DotException("No task description given", TaskError.ERR_USING_DEADLINE);
        }
        String deadline = substrings[1].strip();
        if (deadline.isEmpty()) {
            throw new DotException("No deadline description given", TaskError.ERR_USING_DEADLINE);
        }
        return new String[] {description, deadline};
    }

    /**
     * Validates whether input is in format: event
     * <code>{@literal <desc> /from <start> /to <end>}</code>
     *
     * @param input from user
     * @return { desc, start, end } if input is valid
     * @throws DotException if input is invalid
     */
    public static String[] getArgsIfValidEventFormat(String input) throws DotException {
        // .+ enforces at least one character, but disallows empty string
        // Regex below inspired by
        // https://stackoverflow.com/questions/2912894/how-to-match-any-
        // character-in-regular-expression
        String eventRegex = "event .+ /from .+ /to .+";
        if (!input.matches(eventRegex)) {
            throw new DotException("Wrong format for event.", TaskError.ERR_USING_EVENT);
        }
        int indexOfFirstSlash = input.indexOf("/from");
        int indexOfFSecondSlash = input.indexOf("/to", indexOfFirstSlash + 1);
        String description = input.substring(6, indexOfFirstSlash - 1);
        String start = input.substring(indexOfFirstSlash + 5, indexOfFSecondSlash).strip();
        String end = input.substring(indexOfFSecondSlash + 4);
        return new String[] {description, start, end};
    }

}
