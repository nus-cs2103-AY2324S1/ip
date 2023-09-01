package kevin.parser;

import java.util.ArrayList;
import kevin.exception.KevinException;

/**
 * A class to handle the parsing of text from the user's input.
 */
public class Parser {
    /**
     * Parses the text from the user to make it a QueryObject.
     * @param userInput This is the user input that needs to be parsed.
     * @return Returns a QueryObject containing the Command and arguments.
     * @throws KevinException On the detection of errors.
     */
    public QueryObject prepareArguments(String userInput) throws KevinException {
        String[] querySplit = userInput.trim().split(" ", 2);

        if (querySplit.length == 0) {
            throw new KevinException("Please input a command.");
        }

        Commands command;
        boolean isDone = false;
        ArrayList<String> args = new ArrayList<>();
        args.add(String.valueOf(isDone));

        try {
            command = Commands.valueOf(querySplit[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new KevinException("Command is invalid.");
        }

        if (command == Commands.BYE || command == Commands.LIST) {
            if (querySplit.length > 1) {
                throw new KevinException(command.name() + " command does not take any input.");
            }
        } else if (command == Commands.MARK || command == Commands.UNMARK || command == Commands.DELETE) {
            if (querySplit.length != 2) {
                throw new KevinException(command.name() + " command needs to take one input");
            }
            String[] argumentSplit = querySplit[1].split(" ");
            if (argumentSplit.length > 1) {
                throw new KevinException(command.name() + " command only takes one input.");
            }
            try {
                Integer.parseInt(argumentSplit[0]);
            } catch (NumberFormatException err) {
                throw new KevinException(command.name() + " input must be an integer.");
            }
            args.add(argumentSplit[0]);
        } else if (command == Commands.TODO) {
            if (querySplit.length != 2) {
                throw new KevinException("Todo command needs to follow \"todo {todo name}.\"");
            }
            args.add(querySplit[1]);
        } else if (command == Commands.DEADLINE) {
            String[] argumentSplit = querySplit[1].split(" /by");
            if (argumentSplit.length != 2) {
                throw new KevinException("Deadline command needs to follow " +
                        "\"deadline {deadline name} /by {deadline date}\".");
            }
            args.add(argumentSplit[0]);
            args.add(argumentSplit[1]);
        } else if (command == Commands.EVENT) {
            String[] argumentGetName = querySplit[1].split(" /from", 2);
            if (argumentGetName.length != 2) {
                throw new KevinException("Event command needs to follow " +
                        "\"event {deadline name} /from {event start time} /to {event end time}\".");
            }
            String[] argumentGetDate = argumentGetName[1].split(" /to", 2);
            if (argumentGetDate.length != 2) {
                throw new KevinException("Event command needs to follow " +
                        "\"event {deadline name} /from {event start time} /to {event end time}\".");
            }
            args.add(argumentGetName[0]);
            args.add(argumentGetDate[0]);
            args.add(argumentGetDate[1]);
        }
        return new QueryObject(command, args);
    }
}