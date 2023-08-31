package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Duke;

/**
 * A task with a deadline.
 */
public final class DeadlineTask extends Task {
    private LocalDate dateEnd;

    /**
     * Creates a deadline task.
     * @param task The input string.
     * @throws Duke.WrongFormatException If the input string is in the wrong format.
     */
    public DeadlineTask(String task) throws Duke.WrongFormatException {
        String description = getDescription(task);
        if (description == null) {
            throw new Duke.WrongFormatException("Whopsie daisies! I don't understand that format!");
        }
        this.description = description;
    }

    /**
     * Creates a deadline task.
     * @param isDone Whether the task is done.
     * @param description The description of the task.
     * @param dateEnd The date of the deadline.
     */
    public DeadlineTask(boolean isDone, String description, String dateEnd) {
        this.isDone = isDone;
        this.description = description;
        this.dateEnd = LocalDate.parse(dateEnd);
    }

    @Override
    protected String getTaskTypeString() {
        return squareBracketWrapper("D");
    }

    @Override
    protected String saveToFileString() {
        return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | " + dateEnd;
    }

    @Override
    protected String getDescription(String input) {
        if (input.split(" ", 2).length == 1) {
            return null;
        }

        String[] split = input.split(" ", 2)[1].split(" /by ");

        if (split.length == 1) {
            return null;
        }

        try {
            this.dateEnd = LocalDate.parse(split[1]);
        } catch (DateTimeException e) {
            return null;
        }

        return split[0];
    }

    @Override
    public String toString() {
        return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                + " (by: " + dateEnd.format(formatter) + ")";
    }
}
