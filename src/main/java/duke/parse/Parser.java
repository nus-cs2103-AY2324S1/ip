package duke.parse;

import duke.parse.command.AddCommand;
import duke.parse.command.Command;
import duke.parse.command.DeleteCommand;
import duke.parse.command.EchoCommand;
import duke.parse.command.EmptyCommand;
import duke.parse.command.ExitCommand;
import duke.parse.command.MarkCommand;
import duke.parse.command.ListCommand;
import duke.parse.command.SaveCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Parser {
    public class ParseError extends Exception {
        private ParseError(String message) {
            super(message);
        }
    }

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
                    String[] args = commandArgs[1].split(" ");
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
                                }
                        }
                    }
                    switch (args[0]) {
                        case "todo":
                            return new ListCommand(isExcludingDone, date, ListCommand.Type.TODO);
                        case "deadline":
                            return new ListCommand(isExcludingDone, date, ListCommand.Type.DEADLINE);
                        case "event":
                            return new ListCommand(isExcludingDone, date, ListCommand.Type.EVENT);
                        default:
                            return new ListCommand(isExcludingDone, date, ListCommand.Type.DEFAULT);
                    }
                } else {
                    return new ListCommand(false, null, ListCommand.Type.DEFAULT);
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
                if (commandArgs[1].equals("")) {
                    throw new ParseError("empty to-do task");
                }
                return new AddCommand(new ToDo(commandArgs[1]));

            // add event
            case "event":
                // number of arguments
                if (commandArgs.length != 2) {
                    throw new ParseError("no event provided");
                }

                // /from keyword
                String[] separateByFrom = commandArgs[1].split(" /from ", 2);
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

                try {
                    LocalDateTime startTime = DateTimeManager.inputToDate(separateByTo[0]);
                    LocalDateTime endTime = DateTimeManager.inputToDate(separateByTo[1]);
                    return new AddCommand(new Event(separateByFrom[0], startTime, endTime));
                } catch (DateTimeManager.DateParseException | DateTimeException e) {
                    throw new ParseError("invalid datetime");
                }

            // add deadline
            case "deadline":
                // number of arguments
                if (commandArgs.length != 2) {
                    throw new ParseError("no deadline found");
                }

                String[] separateByBy = commandArgs[1].split(" /by ", 2);
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
                    throw new ParseError("invalid datetime");
                }

            // delete task
            case "delete":
                index = getTaskIndexFromCommand(commandArgs);
                return new DeleteCommand(index);

            // save data to hard disk
            case "save":
                return new SaveCommand();

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
            throw new ParseError("you need to provide a positive integer!");
        }

        return Integer.parseInt(indexString) - 1;
    }
}
