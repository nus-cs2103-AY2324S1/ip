package parser;

import commands.*;

public class Parser {

    public Command parse(String userInput) {
        String commandWord;
        String arguments = null;

        if (isSingleWord(userInput)) {
            commandWord = userInput;
        } else {
            String[] arr = userInput.split(" ", 2);
            commandWord = arr[0];
            arguments = arr[1];
        }

        switch (commandWord) {

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TodoCommand.COMMAND_WORD:
            return prepareTodo(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new UnknownCommand();

        }
    }

    public Command prepareTodo(String args) {

        return new TodoCommand(args);
    }

    public Command prepareDeadline(String args) {

        String[] arr = args.split("/");
        String description = arr[0];
        String endTime = arr[1];
        return new DeadlineCommand(description, endTime);
    }

    public Command prepareEvent(String args) {

        String[] arr = args.split("/");
        String description = arr[0];
        String startTime = arr[1];
        String endTime = arr[2];
        return new EventCommand(description, startTime, endTime);
    }

    public Command prepareMark(String args) {
        int taskNum = Integer.parseInt(args);
        return new MarkCommand(taskNum);
    }

    public Command prepareUnmark(String args) {
        int taskNum = Integer.parseInt(args);
        return new UnmarkCommand(taskNum);
    }

    public Command prepareDelete(String args) {
        int taskNum = Integer.parseInt(args);
        return new DeleteCommand(taskNum);
    }

    public boolean isSingleWord(String input) {
        return input.length() == input.trim().length();
    }

}
