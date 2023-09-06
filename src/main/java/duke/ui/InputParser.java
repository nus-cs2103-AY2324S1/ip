package duke.ui;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Parses user input and returns the corresponding command object.
 */
public class InputParser {
    /**
     * Parses an input line into a command.
     * @param input The string input.
     * @return A Command object representing the input.
     * @throws DukeException If the input has formatting errors.
     */
    public Command parseInput(String input) throws DukeException {
        String[] inputComponents = input.trim().split(" ", 2);
        String commandName = inputComponents[0];
        String content = "";
        if (inputComponents.length > 1) {
            content = inputComponents[1];
        }
        return Command.of(commandName, content);
    }
}
