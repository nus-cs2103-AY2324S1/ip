package bongo.helper;

import bongo.command.*;

public class Parser {
    public Parser() {
    }

    ;

    public static Command parse(String command) throws BongoException {
        String[] input = command.split(" ");
        switch (input[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
            case "delete":
                if (input.length <= 1) throw new BongoException("Please include the task index.");
                int taskIndex = Integer.parseInt(input[1]) - 1;
                if (input[0].equals("mark")) {
                    return new MarkCommand(taskIndex);
                } else if (input[0].equals("unmark")) {
                    return new UnmarkCommand(taskIndex);
                } else if (input[0].equals("delete")) {
                    return new DeleteCommand(taskIndex);
                }
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(input);
            default:
                throw new BongoException();
        }
    }
}
