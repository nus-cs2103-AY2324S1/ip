package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Duke;

/**
 * An event task.
 */
public final class EventTask extends Task {
    private LocalDate dateStart;
    private LocalDate dateEnd;

    /**
     * Creates an event task.
     * @param task The input string.
     * @throws Duke.WrongFormatException If the input string is in the wrong format.
     */
    public EventTask(String task) throws Duke.WrongFormatException {
        String description = getDescription(task);
        if (description == null) {
            throw new Duke.WrongFormatException("Whopsie daisies! I don't understand that format!");
        }
        this.description = description;
    }

    /**
     * Creates an event task.
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     * @param dateStart The start date of the event.
     * @param dateEnd The end date of the event.
     */
    public EventTask(boolean isDone, String description, String dateStart, String dateEnd) {
        this.isDone = isDone;
        this.description = description;
        this.dateStart = LocalDate.parse(dateStart);
        this.dateEnd = LocalDate.parse(dateEnd);
    }

    @Override
    protected String getTaskTypeString() {
        return squareBracketWrapper("E");
    }

    @Override
    protected String saveToFileString() {
        return "EVENT | " + (isDone ? "1" : "0") + " | " + description + " | " + dateStart + " | " + dateEnd;
    }

    @Override
    protected String getDescription(String input) {
        if (input.split(" ", 2).length == 1) {
            return null;
        }

        String[] split = input.split(" ", 2)[1].split(" /from ");
        if (split.length == 1) {
            return null;
        }

        String[] split2 = split[1].split(" /to ");
        if (split2.length == 1) {
            return null;
        }

        try {
            this.dateStart = LocalDate.parse(split2[0]);
            this.dateEnd = LocalDate.parse(split2[1]);
        } catch (DateTimeException e) {
            return null;
        }

        return split[0];
    }

    @Override
    public String toString() {
        return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                + " (from: " + dateStart.format(formatter) + " to: " + dateEnd.format(formatter) + ")";
    }
}
