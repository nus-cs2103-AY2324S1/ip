package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.parser.DateParser;

/** Abstraction for tasks that have deadlines. */
public class Deadline extends Task {

    /** Deadline of task. */
    protected LocalDate deadline;

    /**
     * Constructor to make a new duke.Deadline task.
     *
     * @param description What the deadline task is about.
     * @param isDone Whether the deadline task is done.
     * @param deadline When is the deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "D " + isCompleted + " " + this.description
                + "/by" + this.deadline;
    }

    @Override
    public String update(String specifications) {
        try {
            String[] splits = specifications.split(" ", 2);
            String updateType = splits[0];
            String description = splits.length > 1 ? splits[1] : "";
            switch (updateType) {
            case "/description":
                validateInput(description);
                updateDescription(description);
                break;
            case "/by":
                validateInput(description);
                updateDeadline(description);
                break;
            default:
                throw new IllegalArgumentException("OOPS!!! Update of a deadline task description must have "
                        + "/description <description>. "
                        + "Update of a deadline task deadline must have /by <date>.");
            }
            return "Ok, I've updated the deadline task to the following:\n" + this;
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new IllegalArgumentException(
                    "OOPS!!! Please add a description.");
        }
    }

    private void validateInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(
                    "OOPS!!! Please add a description");
        }
        if (input.contains("/description") || input.contains("/by")) {
            throw new IllegalArgumentException(
                    "OOPS!!! Only 1 attribute can be updated at one time.");
        }
    }

    private void updateDescription(String description) {
        this.description = description;
    }

    private void updateDeadline(String description) {
        LocalDate date = DateParser.parseDate(description);
        this.deadline = date;
    }

    /**
     * Display string representation of task with deadline.
     *
     * @return String representation of task with deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
