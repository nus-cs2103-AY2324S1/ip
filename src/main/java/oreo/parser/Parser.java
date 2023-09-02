package oreo.parser;

import oreo.command.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    public static Command parse(String fullCommand) {
        Scanner tokeniser = new Scanner(fullCommand);
        String command = "";
        try {
            command = tokeniser.next();
        } catch (NoSuchElementException e) {
            return new EmptyCommand();
        }

        switch (command) {
        case("bye") :
            return new ByeCommand();
        case("list") :
            return new ListCommand();
        case("unmark") :
        case("mark") :
            return new MarkUnmarkCommand(command, tokeniser);
        case("delete") :
            return new DeleteCommand(tokeniser);
        default:
            return new AddCommand(command, tokeniser);
        }
    }
}
