package duke.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectFormatException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidNumberException;

public class Parser {

    public Parser() {
    };

    private static class DateTimeWrapper {
        private LocalDate date;
        private LocalTime time;

        private DateTimeWrapper(LocalDate date, LocalTime time) {
            this.date = date;
            this.time = time;
        }
    }

    private static String[] parseInput(String input) {
        int index = input.indexOf(' ');
        if (index > -1) {
            return input.split(" ", 2);
        } else {
            String[] tempString = { input, "" };
            return tempString;
        }
    }

    public static String[] parseFilePath(String input) {
        int index = input.indexOf('/');
        if (index > -1) {
            return input.split("/", 2);
        } else {
            String[] tempString = { input, "" };
            return tempString;
        }
    }

    private static int parseIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
    }

    private static DateTimeWrapper parseDate(String input) throws IncorrectFormatException {
        String[] parsedInput = parseInput(input);
        LocalDate date;
        LocalTime time;
        String i1 = parsedInput[0].trim();
        String i2 = parsedInput[1].trim();

        if (i1.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            date = LocalDate.parse(i1.substring(6, 10) + "-" + i1.substring(3, 5) + "-" + i1.substring(0, 2));
        } else if (i2.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
            date = LocalDate.parse(i2.substring(6, 10) + "-" + i2.substring(3, 5) + "-" + i2.substring(0, 2));
        } else if (i1.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
            date = LocalDate.parse(i1.substring(0, 4) + "-" + i1.substring(5, 7) + "-" + i1.substring(8, 10));
        } else if (i2.matches("[0-9]{4}.[0-9]{2}.[0-9]{2}")) {
            date = LocalDate.parse(i2.substring(0, 4) + "-" + i2.substring(5, 7) + "-" + i2.substring(8, 10));
        } else {
            date = null;
        }

        if (i1.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i1.substring(0, 2) + ":" + i1.substring(3, 5) + ":" + i1.substring(6, 8));
        } else if (i2.matches("[0-9]{2}.[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i2.substring(0, 2) + ":" + i2.substring(3, 5) + ":" + i2.substring(6, 8));
        } else if (i1.matches("[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i1.substring(0, 2) + ":" + i1.substring(3, 5) + ":00");
        } else if (i2.matches("[0-9]{2}.[0-9]{2}")) {
            time = LocalTime.parse(i2.substring(0, 2) + ":" + i2.substring(3, 5) + ":00");
        } else {
            time = null;
        }

        if (time == null && date == null) {
            throw new IncorrectFormatException();
        }

        return new DateTimeWrapper(date, time);
    }

    public static ToDoCommand parseTodo(String input, boolean isDone) throws IncorrectFormatException {
        if (input.equals("")) {
            throw new IncorrectFormatException();
        } else {
            return new ToDoCommand(input, isDone);
        }
    }

    public static DeadlineCommand parseDeadline(String input, boolean isDone) throws IncorrectFormatException {
        int index = input.indexOf(" /by ");

        if (index > -1) {
            String[] parsedInput = input.split(" /by ", 2);

            try {
                String description = parsedInput[0];
                DateTimeWrapper dates = parseDate(parsedInput[1]);
                LocalDate byDate = dates.date;
                LocalTime byTime = dates.time;

                return new DeadlineCommand(description, isDone, byDate, byTime);
            } catch (Exception e) {
                throw new IncorrectFormatException();
            }
        } else {
            throw new IncorrectFormatException();
        }
    }

    public static EventCommand parseEvent(String input, boolean isDone) throws IncorrectFormatException {
        int indexFrom = input.indexOf(" /from ");
        int indexTo = input.indexOf(" /to ");

        if (indexFrom > -1 && indexTo > -1) {
            String[] parsedInput = { input.substring(0, indexFrom), input.substring(indexFrom + 7, indexTo),
                    input.substring(indexTo + 5, input.length()) };

            try {
                String description = parsedInput[0];
                DateTimeWrapper fromDateTime = parseDate(parsedInput[1]);
                DateTimeWrapper toDateTime = parseDate(parsedInput[2]);
                LocalDate fromDate = fromDateTime.date;
                LocalTime fromTime = fromDateTime.time;
                LocalDate toDate = toDateTime.date;
                LocalTime toTime = toDateTime.time;

                // Smart date guesser for incomplete date formats

                if (toDate == null && fromDate != null) {
                    toDate = fromDate;
                }
                if (toDate != null && fromDate == null) {
                    fromDate = toDate;
                }
                if (fromTime == null && toTime != null) {
                    fromTime = toTime;
                }
                if (fromTime != null && toTime == null) {
                    toTime = fromTime;
                }
                return new EventCommand(description, isDone, fromDate, fromTime, toDate, toTime);

            } catch (Exception e) {
                throw new IncorrectFormatException();
            }
        } else {
            throw new IncorrectFormatException();
        }
    }

    public static Command parseFileContent(String input) throws DukeException {
        String[] parsedContent = input.split(" # ");
        boolean isDone = Integer.parseInt(parsedContent[1]) == 1;

        if (parsedContent.length == 3 && parsedContent[0].charAt(0) == 'T') {
            return parseTodo(parsedContent[2], isDone);
        } else if (parsedContent.length == 4 && parsedContent[0].charAt(0) == 'D') {
            return parseDeadline(parsedContent[2] + " /by " + parsedContent[3], isDone);
        } else if (parsedContent.length == 5 && parsedContent[0].charAt(0) == 'E') {
            return parseEvent(parsedContent[2] + " /from " + parsedContent[3] + " /to " + parsedContent[4], isDone);
        } else {
            throw new DukeException("Invalid file format");
        }
    }

    public static Command parse(String input) throws DukeException {
        String[] parsedInput = parseInput(input);
        String command = parsedInput[0].trim();
        String args = parsedInput[1].trim();

        try {
            DukeEnum commandtype = map(command);
            switch (commandtype) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkDoneCommand(true, parseIndex(args));
            case UNMARK:
                return new MarkDoneCommand(false, parseIndex(args));
            case TODO:
                return parseTodo(args, false);
            case DEADLINE:
                return parseDeadline(args, false);
            case EVENT:
                return parseEvent(args, false);
            case DELETE:
                return new DeleteCommand(parseIndex(args));
            default:
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    public static DukeEnum map(String command) throws DukeException {
        for (DukeEnum e : DukeEnum.values()) {
            if (command.equals(e.getText())) {
                return e;
            }
        }
        throw new InvalidCommandException();
    }

}
