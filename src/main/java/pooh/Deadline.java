package pooh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that extends the Task class.
 * <p>
 * The Deadline class is a subclass of the Task class, and it adds two additional attributes:
 * deadlineString and deadlineDateTime to represent the deadline of the task.
 * </p>
 */
public class Deadline extends Task {
    private final String deadlineString;
    private final LocalDate deadlineDateTime;

    /**
     * Constructs a new Deadline object with a description and a deadline.
     * <p>
     * The constructor initializes a Deadline object with a description and a deadline,
     * and it also converts the deadline string into a LocalDate object using the parseDateTime method.
     * </p>
     *
     * @param description    The description of the deadline task.
     * @param deadlineString The string representation of the deadline.
     */
    public Deadline(String description, String deadlineString) {
        super(description);
        this.deadlineString = deadlineString;
        this.deadlineDateTime = parseDateTime(deadlineString);
    }

    /**
     * Writes the task details into a string format suitable for file storage.
     * <p>
     * This function prepares a string that captures the essential attributes of the Deadline object,
     * such as its type (D for Deadline), its completion status, its description, and its deadline.
     * The attributes are separated by a pipe character (|), making it easier to read them back from the file later.
     * </p>
     *
     * @return A string containing the task's details in a format suitable for file storage.
     */
    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s", "D",
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.deadlineString);
    }

    /**
     * Reads task details from a given file and creates a new Deadline object.
     * <p>
     * This function takes an array of strings as its argument, which should contain the task details,
     * and returns a Deadline object based on those details.
     * </p>
     *
     * @param args An array of strings containing the task details.
     *             args[1] should be "1" if the task is marked as done, otherwise not.
     *             args[2] should contain the description of the task.
     *             args[3] should contain the deadline for the task.
     * @return A Deadline object initialized with the given task details.
     */
    public static Deadline readTaskFromFile(String[] args) {
        Deadline newDeadlineTask = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            newDeadlineTask.markAsDone();
        }
        return newDeadlineTask;
    }

    /**
     * Parses a deadline string into a LocalDate object.
     * <p>
     * This function tries to parse a given string into a LocalDate object.
     * If the parsing fails, it returns null.
     * </p>
     *
     * @param deadlineString The string representing the deadline date.
     * @return A LocalDate object if parsing is successful, otherwise null.
     */
    private LocalDate parseDateTime(String deadlineString) {
        try {
            return LocalDate.parse(deadlineString);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * Converts the Deadline object to a string representation.
     * <p>
     * This function overrides the toString method to provide a string representation of the Deadline object,
     * which includes its status (done or not), description, and deadline date.
     * </p>
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        String deadline;
        if (deadlineDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            deadline = deadlineDateTime.format(formatter);
            assert !deadline.isEmpty() : "Error occurred whilst formatting deadlineDateTime";
        } else {
            deadline = deadlineString;
        }
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
