package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Messages;
import duke.Parser;
import duke.exceptions.InsufficientArgumentsException;


/**
 * Represents a task containing the start and end time.
 */
public class Event extends Task {
    public static final String TASK_TYPE = "event";
    public static final String TASK_CODE = "E";
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    /**
     * Creates a new {@code Event} instance.
     *
     * @param description The description of the event.
     * @param isDone      The indication of the event being marked.
     * @param from        The starting datetime of the event.
     * @param to          The ending datetime of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses storage input to create an {@code Event} instance.
     *
     * @param input The storage input string.
     * @return The created Event.
     * @throws InsufficientArgumentsException If the string input has insufficient arguments to
     *                                        create an {@code Event}.
     */
    public static Event parseStorageInput(String input) throws InsufficientArgumentsException {
        boolean isDone = input.charAt(0) == '1';
        String processedInput = input.substring(4);
        String description = processedInput.substring(0, processedInput.indexOf(" | "));
        processedInput = processedInput.substring(processedInput.indexOf(" | ") + 3);
        if (processedInput.length() < Parser.OUTPUT_DATE_TIME_PATTERN.length()) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "from", Event.TASK_TYPE));
        }
        if (processedInput.length() < Parser.STORAGE_DATE_TIME_PATTERN.length() * 2 + 1) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "to", Event.TASK_TYPE));
        }
        // dates in storage should be formatted consistently
        LocalDateTime from =
                Parser.parseDate(processedInput.substring(0,
                        Parser.OUTPUT_DATE_TIME_PATTERN.length() - 1));
        LocalDateTime to =
                Parser.parseDate(processedInput.substring(Parser.OUTPUT_DATE_TIME_PATTERN.length()));
        return new Event(description, isDone, from, to);
    }

    /**
     * Parses user input to create an {@code Event} instance.
     *
     * @param input The user input string.
     * @return The created Event.
     * @throws InsufficientArgumentsException If the string input has insufficient arguments to
     *                                        create an {@code Event}.
     */
    public static Event parseUserInput(String input) throws InsufficientArgumentsException {
        Parser.validateContainsArgument(input, Event.TASK_TYPE, "from");
        Parser.validateContainsArgument(input, Event.TASK_TYPE, "to");
        String[] args = input.split("/from|/to");
        String description = args[0].trim();
        LocalDateTime from = Parser.parseDate(args[1].trim());
        LocalDateTime to = Parser.parseDate(args[2].trim());
        return new Event(description, from, to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("%s | %d | %s | %s-%s", Event.TASK_CODE, this.isDone ? 1 : 0,
                this.description, this.from.format(DateTimeFormatter.ofPattern(
                        Parser.STORAGE_DATE_TIME_PATTERN)),
                this.to.format(DateTimeFormatter.ofPattern(
                        Parser.STORAGE_DATE_TIME_PATTERN)));
    }

    @Override
    public String toString() {
        return String.format("[%s]", Event.TASK_CODE) + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern(
                Parser.OUTPUT_DATE_TIME_PATTERN)) + " to: " + this.to.format(
                DateTimeFormatter.ofPattern(Parser.OUTPUT_DATE_TIME_PATTERN)) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            Event otherEvent = (Event) obj;
            return this.description.equals(otherEvent.description)
                    && this.isDone == otherEvent.isDone && this.from.equals(otherEvent.from)
                    && this.to.equals(otherEvent.to);
        }
        return false;
    }
}
