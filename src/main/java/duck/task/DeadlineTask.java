package duck.task;

import static duck.Parser.OUTPUT_DATE_FORMAT;

import java.time.LocalDate;

import duck.DuckException;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    private LocalDate deadline;

    /**
     * Creates a new DeadlineTask.
     * 
     * @param name   Name of the task.
     * @param isDone Status of the task.
     * @param deadline Deadline of the task.
     */
    public DeadlineTask(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    private String formatDeadline() {
        return deadline.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String stringify() {
        String deadlineString = formatDeadline();
        return "D" + super.stringify()
                + deadlineString.length() + "/" + deadlineString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineTask) {
            DeadlineTask other = (DeadlineTask) obj;
            return super.equals(other) && deadline.equals(other.deadline);
        }
        return false;
    }

    /**
     * Parses the savable string representation of a DeadlineTask.
     * Implementation depends on stringify format.
     * 
     * @param fileLine Savable string representation of the task.
     * @return DeadlineTask parsed from the string representation.
     * @throws DuckException If the string representation is invalid.
     */
    public static DeadlineTask parse(String fileLine) throws DuckException {
        // Finding isDone
        boolean isDone = fileLine.charAt(1) == '1';

        // Finding name
        int slashIndex = fileLine.indexOf("/");
        int nameLength = Integer.parseInt(fileLine.substring(2, slashIndex));
        String name = fileLine.substring(slashIndex + 1, slashIndex + 1 + nameLength);

        // Finding deadline
        int secondSlashIndex = fileLine.indexOf("/", slashIndex + 1);
        String deadlineString = fileLine.substring(secondSlashIndex + 1);
        LocalDate deadline = LocalDate.parse(deadlineString, OUTPUT_DATE_FORMAT);

        return new DeadlineTask(name, isDone, deadline);
    }
}
