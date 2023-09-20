package bellcurvegod.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import bellcurvegod.exception.EmptyByTimeException;
import bellcurvegod.exception.EmptyDeadlineDescriptionException;

/**
 * Encapsulates a task with deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline.
     *
     * @param description Description of the Deadline.
     * @param by          Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline.
     *
     * @param description Description of the Deadline.
     * @param by          Deadline.
     * @param isDone      Whether the Deadline is marked as done.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Generates a Deadline with description given in the input.
     *
     * @param input Input entered by user.
     * @return A Deadline.
     * @throws EmptyDeadlineDescriptionException If deadline is missing.
     * @throws EmptyByTimeException              If by time is missing.
     */
    public static Deadline generateDeadlineFromInput(String input) throws EmptyDeadlineDescriptionException,
            EmptyByTimeException {
        if (input.split(" ").length == 1) {
            throw new EmptyDeadlineDescriptionException("You did not provide any description to this Deadline.\n"
                + "To add a Deadline, enter \"deadline <description> /by <yyyy-mm-dd>\".\n");
        }

        String front = input.split("/")[0];
        if (front.equals(input)) {
            throw new EmptyByTimeException("You did not provide a deadline date to this Deadline.\n"
                + "To add a Deadline, enter \"deadline <description> /by <yyyy-mm-dd>\".\n");
        }
        String[] frontWords = front.split(" ");
        ArrayList<String> desWords = new ArrayList<>(Arrays.asList(frontWords).subList(1, frontWords.length));
        String des = String.join(" ", desWords);

        String back = input.split("/")[1];
        String[] backWords = back.split(" ");
        ArrayList<String> ddlWords = new ArrayList<>(Arrays.asList(backWords).subList(1, backWords.length));
        String ddl = String.join(" ", ddlWords);

        LocalDate deadline = LocalDate.parse(ddl);

        return new Deadline(des, deadline);
    }

    @Override
    public String getDataRepresentation() {
        return "D|" + super.getDataRepresentation() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
