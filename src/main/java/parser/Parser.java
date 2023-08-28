package parser;

import command.*;
import enums.CommandType;
import exception.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Parser {

    public static Command parse(String input) throws BobException {
        String[] inputSplit = input.split(" ", 2);

        CommandType commandType = strToCommandType(inputSplit[0].toUpperCase());
        String argument = "";
        if (inputSplit.length >= 2) {
            argument = inputSplit[1];
        }

        switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
            case UNMARK:
                return Parser.parseMarkCommand(commandType, argument);
            case TODO:
                return Parser.parseTodoCommand(commandType, argument);
            case DEADLINE:
                return Parser.parseDeadlineCommand(commandType, argument);
            case EVENT:
                return Parser.parseEventCommand(commandType, argument);
            case DELETE:
                return Parser.parseDeleteCommand(argument);
            case INVALID:
                throw new BobInvalidCommandException("I'm sorry! I don't understand the command :(");
            default:
                throw new BobInvalidCommandException("I'm sorry! Something went wrong when processing your command :(");
        }
    }

    private static Command parseMarkCommand(CommandType commandType, String argument) throws BobInvalidTaskNumberException {
        if (argument.isBlank()) {
            throw new BobInvalidTaskNumberException("Give me a task number to mark/unmark as done!");
        }
        try {
            return new MarkCommand(Integer.parseInt(argument), commandType == CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new BobInvalidTaskNumberException();
        }
    }

    private static Command parseTodoCommand(CommandType commandType, String argument) throws BobMissingArgumentException, BobInvalidCommandException {
        if (argument.isBlank()) {
            throw new BobMissingArgumentException("The description of your todo should not be empty! Try:\ntodo [description]");
        }
        return new AddCommand(commandType, argument, null, null);
    }

    private static Command parseDeadlineCommand(CommandType commandType, String argument) throws BobMissingArgumentException, BobInvalidCommandException {
        if (argument.isBlank()) {
            throw new BobMissingArgumentException("The description of your deadline should not be empty! Try:\ndeadline [description] /by [duedate]");
        }
        String[] bySplit = argument.split(" /by ", 2);
        if (bySplit.length != 2) {
            throw new BobInvalidCommandException("Incorrect deadline command format! It should be:\ndeadline [description] /by [duedate]");
        }
        return new AddCommand(commandType, bySplit[0], null, bySplit[1]);
    }

    private static Command parseEventCommand(CommandType commandType, String argument) throws BobMissingArgumentException, BobInvalidCommandException {
        if (argument.isBlank()) {
            throw new BobMissingArgumentException("The description of your event should not be empty! Try:\nevent [description] /from [start] /to [end]");
        }
        String[] descSplit = argument.split(" /from ", 2);
        if (descSplit.length != 2) {
            throw new BobInvalidCommandException("Incorrect event command format! It should be:\nevent [description] /from [start] /to [end]");
        }
        String[] periodSplit = descSplit[1].split(" /to ", 2);
        if (periodSplit.length != 2) {
            throw new BobInvalidCommandException("Incorrect event command format! It should be:\nevent [description] /from [start] /to [end]");
        }
        return new AddCommand(commandType, descSplit[0], periodSplit[0], periodSplit[1]);
    }

    private static Command parseDeleteCommand(String argument) throws BobInvalidTaskNumberException {
        if (argument.isBlank()) {
            throw new BobInvalidTaskNumberException("Give me a task number to delete!");
        }
        try {
            return new DeleteCommand(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            throw new BobInvalidTaskNumberException();
        }
    }
    private static CommandType strToCommandType(String s) {
        try {
            return CommandType.valueOf(s);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    public static Task parseStoredTask(String inputString) throws BobException {
        String[] split = inputString.split("\\|");
        String commandType = split[0];

        try {
            switch (commandType) {
                case "T":
                    return new Todo(split[2], Integer.parseInt(split[1]) == 1);
                case "D":
                    return new Deadline(split[2], split[3], Integer.parseInt(split[1]) == 1);
                case "E":
                    return new Event(split[2], split[3], split[4], Integer.parseInt(split[1]) == 1);
            }
            return null;
        } catch (Exception e) {
            throw new BobCorruptFileException("Your save file might be corrupted :(");
        }
    }
}
