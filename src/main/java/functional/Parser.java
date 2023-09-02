package functional;

import commands.*;

public class Parser {

    public static Command parse(String fullCommand) {
        String[] input = fullCommand.split(" ");
        switch (input[0]) {
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand();
            case "unmark":
                return new UnmarkCommand();
            default:
                return new AddCommand();

        }
    }
}
