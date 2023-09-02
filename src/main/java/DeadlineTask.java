import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    LocalDate dueDate;

    private DeadlineTask(boolean isDone, String description, LocalDate dueDate) {
        super(isDone, description);
        this.dueDate = dueDate;
        this.isDone = false;
        // add exceptions for time
    }

    public static DeadlineTask makeDeadline(String allDetails) throws IllegalArgumentException {
        if (allDetails.length() == 0) {
            throw new IllegalArgumentException("Deadline description cannot be empty.");

        }

        String[] descriptionDueTime = allDetails.split("/by ");
        if (descriptionDueTime.length <= 1) {
            throw new IllegalArgumentException("Deadline due date cannot be empty.");
        }

        String description = descriptionDueTime[0];
        String dueDateStr = descriptionDueTime[1];
        LocalDate dueDate = null;
        try {
            dueDate = LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Wrong date format.");
        }
        return new DeadlineTask(false, description, dueDate);
    }

    public static DeadlineTask makeDeadline(String[] taskInputs) throws IllegalArgumentException {
        if (taskInputs.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments." +
                    "Current number of arguments: " + taskInputs.length);
        }

        if (taskInputs[2].length() == 0) {
            throw new IllegalArgumentException("Deadline description cannot be empty.");
        }

        String description = taskInputs[2];
        String dueDateStr = taskInputs[3];
        LocalDate dueDate = null;
        try {
            dueDate = LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Wrong date format.");
        }
        boolean isDone = taskInputs[1].equals("1");
        return new DeadlineTask(isDone, description, dueDate);
    }

    @Override
    public String toString() {
        String dueDate = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + this.getStatusIcon() + "]" + this.description
                + "(by: " + dueDate + ")";
    }

    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "E|" + done + "|" + this.description + "|" + dueDate +"\n";
    }
}
