package kevin.parser;

import java.util.ArrayList;
import kevin.exception.KevinException;

public class Parser {
    public QueryObject prepareArguments(String userInput) throws KevinException {
        String[] querySplit = userInput.trim().split(" ", 2);

        if (querySplit.length == 0) {
            throw new KevinException("Please input a command.");
        }

        Command command;
        boolean isDone = false;
        ArrayList<String> args = new ArrayList<>();
        args.add(String.valueOf(isDone));

        try {
            command = Command.valueOf(querySplit[0].toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new KevinException("Command is invalid.");
        }

        if (command == Command.BYE || command == Command.LIST) {
            if (querySplit.length > 1) {
                throw new KevinException(command.name() + " command does not take any input.");
            }
        } else if (command == Command.MARK || command == Command.UNMARK || command == Command.DELETE) {
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
        } else if (command == Command.TODO) {
            if (querySplit.length != 2) {
                throw new KevinException("Todo command needs to follow \"todo {todo name}.\"");
            }
            args.add(querySplit[1]);
        } else if (command == Command.DEADLINE) {
            String[] argumentSplit = querySplit[1].split(" /by");
            if (argumentSplit.length != 2) {
                throw new KevinException("Deadline command needs to follow " +
                        "\"deadline {deadline name} /by {deadline date}\".");
            }
            args.add(argumentSplit[0]);
            args.add(argumentSplit[1]);
        } else if (command == Command.EVENT) {
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