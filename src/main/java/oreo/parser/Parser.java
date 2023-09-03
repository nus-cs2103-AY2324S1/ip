package oreo.parser;

import oreo.command.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implements class that handles parsing of user input
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class Parser {
    /**
     * Parses input by user
     *
     * @param fullCommand full input by user
     * @return Command instance of the appropriate type base on input.
     */
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
        case("find") :
            return new FindCommand(tokeniser);
        default:
            return new AddCommand(command, tokeniser);
        }
    }
}
