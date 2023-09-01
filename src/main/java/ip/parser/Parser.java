package Parser;

import Exception.UnknownCommandException;
import Exception.EmptyDescriptionException;

public class Parser {
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
