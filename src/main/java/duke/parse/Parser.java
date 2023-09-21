package duke.parse;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.parse.command.AddCommand;
import duke.parse.command.Command;
import duke.parse.command.DeleteCommand;
import duke.parse.command.EchoCommand;
import duke.parse.command.EmptyCommand;
import duke.parse.command.ExitCommand;
import duke.parse.command.FindCommand;
import duke.parse.command.ListCommand;
import duke.parse.command.MarkCommand;
import duke.parse.command.SaveCommand;
import duke.parse.command.update.UpdateDeadlineCommand;
import duke.parse.command.update.UpdateEndTimeCommand;
import duke.parse.command.update.UpdateNameCommand;
import duke.parse.command.update.UpdateStartTimeCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Deals with parsing input from user.
 */
public class Parser {
    /**
     * Thrown when the input from user cannot be parsed properly.
     */
    public static class ParseError extends Exception {
        private ParseError(String message) {
            super(message);
        }
    }

    /**
     * Parses a given input.
     * Check out the interact method of Duke class to view available commands.
     * @param input The input from the user.
     * @return The command to be executed on the bot.
     * @throws ParseError When the input cannot be properly interpreted.
     */
    public static Command parse(String input) throws ParseError {
        String[] commandArgs = input.split(" ", 2);
        int index;
        switch (commandArgs[0]) {
        case "":
            return new EmptyCommand();

        case "exit":
        case "bye":
            return new ExitCommand();

        // show list
        case "list":
            if (commandArgs.length != 1) {
                return Parser.parseListCommand(commandArgs[1]);
            } else {
                return new ListCommand(false, null, Task.Type.DEFAULT);
            }

        // mark as done
        case "mark":
            index = Parser.getTaskIndexFromCommand(commandArgs);
            return new MarkCommand(true, index);

        // mark as not done
        case "unmark":
            index = Parser.getTaskIndexFromCommand(commandArgs);
            return new MarkCommand(false, index);

        // add to-do
        case "todo":
            if (commandArgs.length != 2) {
                throw new ParseError("no to-do task provided");
            }
            return Parser.parseTodo(commandArgs[1]);

        // add event
        case "event":
            if (commandArgs.length != 2) {
                throw new ParseError("no event provided");
            }
            return Parser.parseEventInput(commandArgs[1]);

        // add deadline
        case "deadline":
            if (commandArgs.length != 2) {
                throw new ParseError("no deadline found");
            }
            return Parser.parseDeadline(commandArgs[1]);

        // delete task
        case "delete":
            index = getTaskIndexFromCommand(commandArgs);
            return new DeleteCommand(index);

        // save data to hard disk
        case "save":
            return new SaveCommand();

        // find task containing given description
        case "find":
            if (commandArgs.length != 2) {
                throw new ParseError("no search parameter provided");
            }
            String query = commandArgs[1];
            return new FindCommand(query);

        // update a task
        case "update":
            if (commandArgs.length != 2) {
                throw new ParseError("no update parameter found");
            }
            return Parser.parseUpdate(commandArgs[1]);

        // anything else
        default:
            return new EchoCommand(input);
        }
    }

    private static int getTaskIndexFromCommand(String[] commandArgs) throws ParseError {
        // check for number of arguments
        if (commandArgs.length != 2) {
            throw new ParseError("you have provided wrong number of arguments!");
        }

        // check if second argument is positive integer
        String indexString = commandArgs[1];
        if (indexString.matches("0+") || !indexString.matches("\\d+")) {
            throw new ParseError("you need to provide a positive integer");
        }

        return Integer.parseInt(indexString) - 1;
    }

    private static Command parseListCommand(String listCommandString) throws ParseError {
        String[] args = listCommandString.split(" ");
        boolean isExcludingDone = false;
        LocalDate date = null;
        for (String arg: args) {
            switch (arg) {
            case "todo":
            case "deadline":
            case "event":
                break;
            case "-d":
                isExcludingDone = true;
                break;
            default:
                try {
                    date = DateTimeManager.parseDate(arg);
                } catch (DateTimeManager.DateParseException e) {
                    throw new ParseError("unrecognised \"" + arg + "\"");
                } catch (DateTimeException e) {
                    throw new ParseError("cannot parse date " + arg);
                }
            }
        }
        switch (args[0]) {
        case "todo":
            return new ListCommand(isExcludingDone, date, Task.Type.TODO);
        case "deadline":
            return new ListCommand(isExcludingDone, date, Task.Type.DEADLINE);
        case "event":
            return new ListCommand(isExcludingDone, date, Task.Type.EVENT);
        default:
            return new ListCommand(isExcludingDone, date, Task.Type.DEFAULT);
        }
    }

    private static Command parseEventInput(String eventInput) throws ParseError {
        // /from keyword
        String[] separateByFrom = eventInput.split(" /from ", 2);
        // no empty event
        if (separateByFrom[0].equals("")) {
            throw new ParseError("empty event");
        }
        // /from keyword must exist
        if (separateByFrom.length != 2) {
            throw new ParseError("keyword '/from' not found");
        }

        // /to keyword
        String[] separateByTo = separateByFrom[1].split(" /to ", 2);
        // no empty start time
        if (separateByTo[0].equals("")) {
            throw new ParseError("empty start time");
        }
        // /to keyword must exist
        if (separateByTo.length != 2) {
            throw new ParseError("keyword '/to' not found");
        }
        // no empty end time
        if (separateByTo[1].equals("")) {
            throw new ParseError("empty end time");
        }

        LocalDateTime startTime;
        LocalDateTime endTime;
        try {
            startTime = DateTimeManager.inputToDate(separateByTo[0]);
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            throw new ParseError("invalid datetime " + separateByTo[0]);
        }
        try {
            endTime = DateTimeManager.inputToDate(separateByTo[1]);
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            throw new ParseError("invalid datetime " + separateByTo[1]);
        }

        if (startTime.compareTo(endTime) >= 0) {
            throw new ParseError("end time must be after start time");
        }

        return new AddCommand(new Event(separateByFrom[0], startTime, endTime));
    }

    private static Command parseTodo(String todoInput) throws ParseError {
        if (todoInput.equals("")) {
            throw new ParseError("empty to-do task");
        }
        return new AddCommand(new ToDo(todoInput));
    }

    private static Command parseDeadline(String deadlineInput) throws ParseError {
        String[] separateByBy = deadlineInput.split(" /by ", 2);
        // /by keyword must exist
        if (separateByBy.length != 2) {
            throw new ParseError("keyword '/by' not found");
        }
        // no empty deadline
        if (separateByBy[0].equals("")) {
            throw new ParseError("empty deadline task");
        }
        // no empty end time
        if (separateByBy[1].equals("")) {
            throw new ParseError("empty deadline time");
        }

        try {
            LocalDateTime dateTime = DateTimeManager.inputToDate(separateByBy[1]);
            return new AddCommand(new Deadline(separateByBy[0], dateTime));
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            throw new ParseError("invalid datetime " + separateByBy[1]);
        }
    }

    private static Command parseUpdate(String updateInput) throws ParseError {
        String[] splitBySpace = updateInput.split(" ", 3);
        if (splitBySpace.length != 3) {
            throw new ParseError("not enough arguments for an update command, need 3");
        }

        if (splitBySpace[1].matches("0+") || !splitBySpace[1].matches("\\d+")) {
            throw new ParseError("positive integer expected, but got " + splitBySpace[1]);
        }
        int index = Integer.parseInt(splitBySpace[1]) - 1;

        switch (splitBySpace[0]) {
        case "name":
            return new UpdateNameCommand(index, splitBySpace[2]);
        case "deadline":
            try {
                LocalDateTime newDeadlineTime = DateTimeManager.inputToDate(splitBySpace[2]);
                return new UpdateDeadlineCommand(index, newDeadlineTime);
            } catch (DateTimeManager.DateParseException | DateTimeException e) {
                throw new ParseError("datetime of new deadline cannot be parsed properly, got " + splitBySpace[2]);
            }
        case "starttime":
            try {
                LocalDateTime newStartTime = DateTimeManager.inputToDate(splitBySpace[2]);
                return new UpdateStartTimeCommand(index, newStartTime);
            } catch (DateTimeManager.DateParseException | DateTimeException e) {
                throw new ParseError("datetime of new start time cannot be parsed properly");
            }
        case "endtime":
            try {
                LocalDateTime newEndTime = DateTimeManager.inputToDate(splitBySpace[2]);
                return new UpdateEndTimeCommand(index, newEndTime);
            } catch (DateTimeManager.DateParseException | DateTimeException e) {
                throw new ParseError("datetime of new end time cannot be parsed properly");
            }
        default:
            throw new ParseError("unrecognised " + splitBySpace[0]);
        }
    }
}
