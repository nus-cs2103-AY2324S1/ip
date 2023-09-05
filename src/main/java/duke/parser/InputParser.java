package duke.parser;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Parses user input and returns the corresponding command object.
 */
public class InputParser {
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
