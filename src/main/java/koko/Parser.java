package koko;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public boolean hasInvalidCharacters(String input) {
        return input.contains("|");
    }

    public Command parseCommandType(String input) throws DukeException {
        if (input == null || input.trim().isEmpty()) {
            throw new DukeException("Command input cannot be null or empty. Each message should start with one of the following commands: list, mark, unmark, todo, deadline, event, find");
        }

        try {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Each message should start with one of the following commands: list, mark, unmark, todo, deadline, event, find");
        } catch (Exception e) {
            throw new DukeException("Unexpected error while parsing command: " + e.getMessage());
        }
    }

    public String parseRemainingArgs(Command commandType, String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String remaining = parts.length > 1 ? parts[1] : "";
        if (remaining.isEmpty()) {
            switch (commandType) {
                case MARK:
                case UNMARK:
                    throw new DukeException("Please specify a task number.");
                case TODO:
                    throw new DukeException("Description for todo cannot be empty.");
                case DEADLINE:
                    throw new DukeException("Description and date for deadline cannot be empty.");
                case EVENT:
                    throw new DukeException("Description, start date, and end date for event cannot be empty.");
                case FIND:
                    throw new DukeException("Please specify a search term.");
            }
        }
        return remaining;
    }

    public ParsedDeadlineArgs parseDeadlineString(String remaining) throws DukeException {
        String[] parts = remaining.split("/by ", 2);
        if (parts.length < 2) {
            throw new DukeException("Missing '/by' or date for deadline.");
        }
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(parts[1].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Deadline /by date should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }
        return new ParsedDeadlineArgs(parts[0].trim(), byDate);
    }

    public ParsedEventArgs parseEventString(String remaining) throws DukeException {
        String[] splitByTo = remaining.split("/to ", 2);
        if (splitByTo.length < 2) {
            throw new DukeException("Missing '/to' or end date for event.");
        }
        String[] splitByFrom = splitByTo[0].split("/from ", 2);
        if (splitByFrom.length < 2) {
            throw new DukeException("Missing '/from' or start date for event.");
        }
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(splitByFrom[1].trim());
            endDate = LocalDate.parse(splitByTo[1].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Event /from or /to dates should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }

        return new ParsedEventArgs(splitByFrom[0].trim(), startDate, endDate);
    }

    public ParsedTodoArgs parseTodoString(String remaining) throws DukeException {
        if (remaining.isEmpty()) {
            throw new DukeException("Description for todo cannot be empty.");
        }
        return new ParsedTodoArgs(remaining);
    }

    public static abstract class ParsedTaskArgs {
        public final String taskName;

        public ParsedTaskArgs(String taskName) {
            this.taskName = taskName;
        }
    }

    public static class ParsedDeadlineArgs extends ParsedTaskArgs {
        public final LocalDate byDate;

        public ParsedDeadlineArgs(String taskName, LocalDate byDate) {
            super(taskName);
            this.byDate = byDate;
        }
    }

    public static class ParsedEventArgs extends ParsedTaskArgs {
        public final LocalDate startDate;
        public final LocalDate endDate;

        public ParsedEventArgs(String eventName, LocalDate startDate, LocalDate endDate) {
            super(eventName);
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    public static class ParsedTodoArgs extends ParsedTaskArgs {

        public ParsedTodoArgs(String taskName) {
            super(taskName);
        }
    }

}
