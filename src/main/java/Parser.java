import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    public static Fluke.Command parseCommand(String nextCommand) throws InvalidInputException {
        if (nextCommand.equals("bye")) {
            return Fluke.Command.BYE;
        } else if (nextCommand.equals("list")) {
            return Fluke.Command.LIST;
        } else if (nextCommand.startsWith("mark")) {
            return Fluke.Command.MARK;
        } else if (nextCommand.startsWith("unmark")) {
            return Fluke.Command.UNMARK;
        } else if (nextCommand.startsWith("delete")) {
            return Fluke.Command.DELETE;
        } else if (nextCommand.startsWith("todo")) {
            return Fluke.Command.TODO;
        } else if (nextCommand.startsWith("deadline")) {
            return Fluke.Command.DEADLINE;
        } else if (nextCommand.startsWith("event")) {
            return Fluke.Command.EVENT;
        } else {
            throw new InvalidInputException();
        }
    }

    public static Task parseTask(String taskString) throws DukeException {
        Fluke.Command taskType;
        boolean isMarked;
        // parse type
        Pattern typePattern = Pattern.compile("\\[[TDE]]");
        Matcher typeMatcher = typePattern.matcher(taskString);
        boolean typeFound = typeMatcher.find();
        if (!typeFound) {
            throw new SaveFileParsingException();
        }
        switch (typeMatcher.group()) {
        case "[T]":
            taskType = Fluke.Command.TODO;
            break;
        case "[D]":
            taskType = Fluke.Command.DEADLINE;
            break;
        case "[E]":
            taskType = Fluke.Command.EVENT;
            break;
        default:
            throw new SaveFileParsingException();
        }

        // parse mark
        Pattern markPattern = Pattern.compile("\\[[X ]]");
        Matcher markMatcher = markPattern.matcher(taskString);
        boolean markFound = markMatcher.find();
        if (!markFound) {
            throw new SaveFileParsingException();
        }
        String mark = markMatcher.group();
        switch (mark) {
        case "[ ]":
            isMarked = false;
            break;
        case "[X]":
            isMarked = true;
            break;
        default:
            throw new SaveFileParsingException();
        }

        String taskDesc = taskString.substring(7);
        if (taskType == Fluke.Command.TODO) {
            return new Todo(taskDesc, isMarked);
        } else if (taskType == Fluke.Command.DEADLINE) {
            // parse by date
            int bracketStartIndex = taskDesc.indexOf('(');
            int bracketEndIndex = taskDesc.indexOf(')');
            if (bracketStartIndex < 0 || bracketEndIndex < 0) {
                throw new SaveFileParsingException();
            }
            String desc = taskDesc.substring(0, bracketStartIndex).trim();
            String by = taskDesc.substring(bracketStartIndex + 4, bracketEndIndex).trim();
            LocalDate date = LocalDate.parse(by, DATE_TIME_FORMATTER);
            return new Deadline(desc, isMarked, date.toString());
        } else if (taskType == Fluke.Command.EVENT) {
            int bracketStartIndex = taskDesc.indexOf('(');
            if (bracketStartIndex < 0) {
                throw new SaveFileParsingException();
            }
            String desc = taskDesc.substring(0, bracketStartIndex).trim();
            // parse from date
            Pattern fromPattern = Pattern.compile("from:.+to:");
            Matcher fromMatcher = fromPattern.matcher(taskDesc);
            boolean fromFound = fromMatcher.find();
            if (!fromFound) {
                throw new SaveFileParsingException();
            }
            String from = fromMatcher.group().substring(5).replaceFirst("to:", "").trim();
            LocalDate fromDate = LocalDate.parse(from, DATE_TIME_FORMATTER);
            // parse to date
            Pattern toPattern = Pattern.compile("to:.+\\)");
            Matcher toMatcher = toPattern.matcher(taskDesc);
            boolean toFound = toMatcher.find();
            if (!toFound) {
                throw new SaveFileParsingException();
            }
            String to = toMatcher.group().substring(3).replaceFirst("\\)", "").trim();
            LocalDate toDate = LocalDate.parse(to, DATE_TIME_FORMATTER);
            return new Event(desc, isMarked, fromDate.toString(), toDate.toString());
        }
        throw new SaveFileParsingException();
    }

    public static String parseTodoCommand(String command) throws DukeException {
        if (command.length() <= 5) {
            // command is too short, description is invalid
            throw new EmptyDescriptionException();
        }
        return command.substring(5);
    }

    public static String[] parseDeadlineCommand(String command) throws DukeException {
        if (command.length() <= 9) {
            // command is too short, description is invalid
            throw new EmptyDescriptionException();
        }
        String str = command.substring(9);
        int byIndex = str.indexOf("/by");
        if (byIndex < 0) {
            throw new InvalidInputException();
        }
        String description = str.substring(0, byIndex - 1);
        String by = str.substring(byIndex + 4);
        return new String[]{description, by};
    }

    public static String[] parseEventCommand(String command) throws DukeException {
        if (command.length() <= 6) {
            // command is too short, description is invalid
            throw new EmptyDescriptionException();
        }
        String str = command.substring(6);
        int fromIndex = str.indexOf("/from");
        int toIndex = str.indexOf("/to");
        if (fromIndex < 0 || toIndex < 0) {
            throw new InvalidInputException();
        }
        String description = str.substring(0, fromIndex - 1);
        String from = str.substring(fromIndex + 6, toIndex - 1);
        String to = str.substring(toIndex + 4);
        return new String[]{description, from, to};
    }

    public static int parseDeleteCommand(String nextCommand) throws DukeException {
        if (nextCommand.length() <= 7) {
            throw new InvalidInputException();
        }
        int taskNumber = obtainTaskNumber(nextCommand.substring(7));
        return taskNumber - 1;
    }

    public static int parseMarkAsDoneCommand(String nextCommand) throws DukeException {
        if (nextCommand.length() <= 5) {
            throw new InvalidInputException();
        }
        int taskNumber = obtainTaskNumber(nextCommand.substring(5));
        return taskNumber - 1;
    }

    public static int parseMarkAsUndoneCommand(String nextCommand) throws DukeException {
        if (nextCommand.length() <= 7) {
            throw new InvalidInputException();
        }
        int taskNumber = obtainTaskNumber(nextCommand.substring(7));
        return taskNumber - 1;
    }

    private static int obtainTaskNumber(String taskNumberString) throws InvalidInputException {
        try {
            return Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
