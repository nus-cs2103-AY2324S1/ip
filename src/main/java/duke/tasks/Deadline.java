package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import duke.Messages;
import duke.Parser;
import duke.exceptions.InsufficientArgumentsException;

/**
 * Represents a task containing a description, and the due time.
 */
public class Deadline extends Task {
    public static final String TASK_TYPE = "deadline";
    public static final String TASK_CODE = "D";
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    /**
     * Creates a new {@code Deadline} instance.
     *
     * @param description The description of the deadline.
     * @param isDone      The indication of the deadline being marked.
     * @param by          The due date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Parses storage input to create a {@code Deadline} instance.
     *
     * @param input The storage input string.
     * @return The created Deadline.
     * @throws InsufficientArgumentsException If the string input has insufficient arguments to
     *                                        create an {@code Deadline}.
     */
    public static Deadline parseStorageInput(String input) throws InsufficientArgumentsException {
        boolean isDone = input.charAt(0) == '1';
        String processedInput = input.substring(4);
        String description = processedInput.substring(0, processedInput.indexOf(" | "));
        String substring = processedInput.substring(processedInput.indexOf(" | ") + 3);
        if (Objects.equals(substring, "")) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "by", Deadline.TASK_TYPE));
        }
        LocalDateTime by = Parser.parseDate(substring);
        return new Deadline(description, isDone, by);
    }

    /**
     * Parses user input to create a {@code Deadline} instance.
     *
     * @param input The user input string.
     * @return The created Deadline.
     * @throws InsufficientArgumentsException If the string input has insufficient arguments to
     *                                        create an {@code Deadline}.
     */
    public static Deadline parseUserInput(String input) throws InsufficientArgumentsException {
        Parser.validateContainsArgument(input, Deadline.TASK_TYPE, "by");
        String[] args = input.split("/by");
        String description = args[0].trim();
        LocalDateTime by = Parser.parseDate(args[1].trim());
        return new Deadline(description, by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("%s | %d | %s | %s", Deadline.TASK_CODE, this.isDone ? 1 : 0,
                this.description,
                this.by.format(DateTimeFormatter.ofPattern(Parser.STORAGE_DATE_TIME_PATTERN)));
    }

    @Override
    public String toString() {
        return String.format("[%s]", Deadline.TASK_CODE) + super.toString() + " (by: " + this.by.format(
                DateTimeFormatter.ofPattern(Parser.OUTPUT_DATE_TIME_PATTERN)) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline otherDeadline = (Deadline) obj;
            return this.description.equals(otherDeadline.description)
                    && this.isDone == otherDeadline.isDone && this.by.equals(otherDeadline.by);
        }
        return false;
    }
}
