package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class extends from the Task class and contains an additional
 * LocalDate Object whose information is parsed from the Task's name.
 */
public class Deadline extends Task {

    LocalDate deadline;

    /**
     * Creates a Deadline object.
     *
     * @param done Boolean representation of completion.
     * @param name Name of deadline task.
     */
    Deadline(boolean done, String name) {
        super(done, name);
        String[] dateParse = name.split("by: ");
        String dateParse2 = dateParse[1].split("\\)")[0];
        LocalDate temp = LocalDate.parse(dateParse2);
        this.deadline = temp;
    }

    /**
     * Creates a Deadline object with default false completion.
     *
     * @param name Name of deadline Task.
     */
    Deadline(String name) {
        this(false, name);
    }

    /**
     * Returns the type of task in String format.
     *
     * @return "D" for Deadline.
     */
    @Override
    public String taskType() {
        return "D";
    }

    /**
     * Returns a String representation of the Deadline object.
     *
     * @return "[D] [X]/[ ] [name] [MMM DD YYYY]
     */
    @Override
    public String toString() {
        String parsedName = this.name.split(" \\(")[0];
        String stringDate = this.deadline
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        String doneString = this.done ? "[X] " : "[ ] ";

        String finalOutput = "[D] " + doneString
            + parsedName + String.format(" (by: %s)", stringDate);

        return finalOutput;
    }
}
