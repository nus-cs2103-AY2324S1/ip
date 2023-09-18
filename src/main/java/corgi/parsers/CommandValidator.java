package corgi.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * The CommandValidator class provides methods for validating command arguments.
 */
public class CommandValidator {

    /**
     * Validates the presence and number of expected arguments in a command.
     *
     * @param command The input command string to be validated.
     * @param arguments A set of expected arguments that should be present in the command.
     * @throws InvalidCommandFormatException If the command does not contain all the expected arguments
     *                                        or contains duplicate arguments.
     */
    public void validateArguments(String command, Set<String> arguments)
            throws InvalidCommandFormatException {
        List<String> errorMsg = new ArrayList<>();
        boolean isValid = true;
        for (String argument : arguments) {
            long numOfArg = new ArrayList<String>(Arrays.asList(command.split(" ")))
                    .stream()
                    .filter(x -> x.equals(argument))
                    .count();

            if (numOfArg == 1) {
                continue;
            }

            isValid = false;

            if (numOfArg == 0) {
                errorMsg.add("Missing argument " + "\"" + argument + "\" !");
            } else {
                errorMsg.add("Invalid number of argument " + "\"" + argument + "\" !");
            }
        }

        String fullErrorMsg = String.join("\n", errorMsg);

        if (!isValid) {
            throw new InvalidCommandFormatException(fullErrorMsg);
        }
    }

    /**
     * Checks if a command has no arguments.
     *
     * @param command The input command string to be checked for the absence of arguments.
     * @return true if the command has no arguments, false otherwise.
     */
    public boolean hasNoArgument(String command) {
        String[] splitWithSpace = command.split(" ");
        return splitWithSpace.length == 1;
    }
}
