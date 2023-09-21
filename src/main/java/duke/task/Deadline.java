package duke.task;

import java.time.LocalDate;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    private LocalDate due;

    /**
     * Constructs a Deadline object.
     * @param taskContent
     * @param due
     */
    public Deadline(String taskContent, LocalDate due) {

        super(taskContent);
        this.due = due;
    }

    /**
     * Returns task string.
     * @return the string of deadline event
     */
    @Override
    public String toString() {
        String statusAndTaskContent = super.toString();
        return String.format("  [D] %s (by: %s)", statusAndTaskContent, due.format(formatter));
    }

    /**
     * Creates Deadline task.
     * @param status
     * @param description
     * @param due
     * @return a new deadline task
     */
    public static Deadline create(String status, String description, String due) {
        Deadline task = new Deadline(description, LocalDate.parse(due));
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    /**
     * Constructs the line for deadline task to be saved into disc.
     * @return the constructed string
     */
    public String saveToFileLine() {
        return String.format("D | %s | %s\n", super.saveToFileLine(), due.toString());
    }
}
