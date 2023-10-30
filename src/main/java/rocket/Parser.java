package rocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static final DateTimeFormatter uglyDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter prettyDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Parses the line command that the user types into a Command
     * @param fullCommand the String of user input.
     * @return a Command that will be executed.
     * @throws RocketException if user enters an invalid command.
     */
    public static Command parse(String fullCommand) throws RocketException{
        String command;
        String arguments;

        int firstWordIndex = fullCommand.indexOf(' ');
        if (firstWordIndex == -1) {
            command = fullCommand;
            arguments = "";
        } else {
            command = fullCommand.substring(0, firstWordIndex);
            arguments = fullCommand.substring(firstWordIndex + 1);
        }

        switch (command) {
        case "bye": {
            return new ExitCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "find": {
            return new FindCommand(arguments);
        }
        case "mark": {
            return new MarkCommand(Integer.parseInt(arguments) - 1);
        }
        case "unmark": {
            return new UnmarkCommand(Integer.parseInt(arguments) - 1);
        }
        case "delete": {
            return new DeleteCommand(Integer.parseInt(arguments) - 1);
        }
        case "todo": {
            return new TodoCommand(arguments, false);
        }
        case "deadline": {
            DeadlineInfo deadlineInfo = parseDeadlineStr(arguments, uglyDateTimeFormatter);
            String description = deadlineInfo.getDescription();
            LocalDateTime deadline = deadlineInfo.getDeadline();

            return new DeadlineCommand(description, false, deadline);
        }
        case "event": {
            EventInfo eventInfo = parseEventStr(arguments, uglyDateTimeFormatter);
            String description = eventInfo.getDescription();
            LocalDateTime startDate = eventInfo.getStartDate();
            LocalDateTime endDate = eventInfo.getEndDate();

            return new EventCommand(description, false, startDate, endDate);
        }
        default: {
            throw new RocketInvalidCommandException();
        }
        }
    }

    public static class DeadlineInfo {
        private String description;
        private LocalDateTime deadline;

        public DeadlineInfo(String description, LocalDateTime deadline) {
            this.description = description;
            this.deadline = deadline;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getDeadline() {
            return deadline;
        }
    }

    /**
     * Parses a deadline string
     * @param input the deadline string
     * @param dateTimeFormatter the date time formatter - ugly or pretty
     * @return a new DeadlineInfo object representing the deadline information
     * @throws RocketIllegalArgumentException if there are invalid arguments
     */
    public static DeadlineInfo parseDeadlineStr(String input, DateTimeFormatter dateTimeFormatter) throws RocketIllegalArgumentException{
        int byIndex = input.indexOf("by");
        if (byIndex == -1) {
            throw new RocketIllegalArgumentException("Missing 'by' keyword");
        }

        String description = input.substring(0, byIndex - 2).trim();
        String deadlineStr = input.substring(byIndex + 3)
                .replace(')', ' ')
                .trim();

        if (description.isEmpty()) {
            throw new RocketIllegalArgumentException("The description of a deadline is empty");
        }
        if (deadlineStr.isEmpty()) {
            throw new RocketIllegalArgumentException("The deadline of a deadline is empty");
        }

        LocalDateTime by = LocalDateTime.parse(deadlineStr, dateTimeFormatter);

        return new DeadlineInfo(description, by);
    }

    public static class EventInfo {
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public EventInfo(String description, LocalDateTime startDate, LocalDateTime endDate) {
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }
    }

    /**
     * Parses an event string
     * @param input the event string
     * @param dateTimeFormatter the date time formatter - ugly or pretty
     * @return a new EventInfo object representing the event information
     * @throws RocketIllegalArgumentException if there are invalid arguments
     */
    public static EventInfo parseEventStr(String input, DateTimeFormatter dateTimeFormatter) throws RocketIllegalArgumentException {
        int fromIndex = input.indexOf("from") ;
        String description = input.substring(0, fromIndex - 2);

        if (description.isEmpty()) {
            throw new RocketIllegalArgumentException("The description of an event is empty.");
        }

        String duration = input.substring(fromIndex + 5)
                .trim();

        if (duration.isBlank()) {
            throw new RocketIllegalArgumentException("The duration of an event is empty.");
        }

        int toIndex = duration.indexOf("to");
        LocalDateTime startDate = LocalDateTime.parse(
                duration.substring(0, toIndex - 1).trim(),
                dateTimeFormatter
        );
        LocalDateTime endDate = LocalDateTime.parse(
                duration.substring(toIndex + 3)
                        .replace(')', ' ')
                        .trim(),
                dateTimeFormatter
        );

        return new EventInfo(description, startDate, endDate);
    }
}
