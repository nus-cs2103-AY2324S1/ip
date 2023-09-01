package duke;

import duke.exception.UnknownCommandException;
import duke.exception.EmptyDescriptionException;

/**
 * Represents the parser of the chat bot.
 * A <code>Parser</code> object corresponds to the parser of the chat bot.
 */
public class Parser {
    /**
     * Parses the user input into the command and the rest of the input.
     * @param input The user input.
     * @return The command and the rest of the input.
     * @throws UnknownCommandException If the command is not recognised.
     * @throws EmptyDescriptionException If the description of the command is empty.
     */
    public static String[] parseCommand(String input) throws UnknownCommandException, EmptyDescriptionException {
        String[] words = input.split(" ", 2); // splits into the command and the rest

        String command = words[0];
        boolean validCommand2Words = command.equals("todo") || command.equals("deadline") || command.equals("event") ||
                command.equals("mark") || command.equals("unmark") || command.equals("delete");
        boolean validCommand1Word = command.equals("bye") || command.equals("list") || command.equals("help");

        if (!validCommand2Words && !validCommand1Word) {
            throw new UnknownCommandException();
        } else if (validCommand2Words && words.length < 2) {
            throw new EmptyDescriptionException();
        }

        return words;
    }
}
