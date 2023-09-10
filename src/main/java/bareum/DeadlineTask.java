package bareum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class implements a deadline with a due date.
 */
public class DeadlineTask extends Task {
    /**
     * Due date of the deadline.
     */
    LocalDate dueDate;

    /**
     * Creates a new deadline using the input completion status, description and due date.
     * @param isDone Completion status of the deadline.
     * @param description Description of the deadline.
     * @param dueDate Due date of the deadline in ISO format.
     */
    private DeadlineTask(boolean isDone, String description, LocalDate dueDate) {
        super(isDone, description);
        this.dueDate = dueDate;
        this.isDone = false;
    }

    /**
     * Creates a new uncompleted deadline using the inputs from the user.
     * @param description Description of the deadline.
     * @param dueDateStr Due date of the deadline in ISO format as a string read from the user input.
     * @return New uncompleted DeadlineTask with the corresponding description and dueDate.
     * @throws BareumException If date cannot be parsed.
     */
    public static DeadlineTask makeDeadline(String description, String dueDateStr) throws BareumException {
        LocalDate dueDate = null;
        try {
            dueDate = LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new BareumException("Oops! Please enter the due date in YYYY-MM-DD :(\n" +
                    "Correct format: deadline <description> /by <due date in YYYY-MM-DD>");
        }
        return new DeadlineTask(false, description, dueDate);
    }

    /**
     * Creates a new deadline using the inputs from a saved deadline.
     * @param taskInputs Type, completion status, description and due date of the saved task.
     * @return New deadline with the corresponding completion status, description and dueDate.
     */
    public static DeadlineTask makeDeadline(String[] taskInputs) {
        boolean isDone = taskInputs[1].equals("1");
        String description = taskInputs[2];
        String dueDateStr = taskInputs[3];
        LocalDate dueDate = LocalDate.parse(dueDateStr);

        return new DeadlineTask(isDone, description, dueDate);
    }

    @Override
    public String toString() {
        String dueDate = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[D][" + this.getStatusIcon() + "] " + this.description
                + "(by: " + dueDate + ")";
    }

    /**
     * Create a string representation of the details of the deadline for saving into the hard disk.
     * @return String representation of the details of the deadline.
     */
    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "D|" + done + "|" + this.description + "|" + dueDate +"\n";
    }
}
