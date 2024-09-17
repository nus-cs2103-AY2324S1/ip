package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Class representing a task which has a deadline */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Class constructor specifying the description of the deadline task. It is called when the user adds a deadline
     * task into Duke.
     * @param description the string description of the task.
     * @param by the deadline of the task as a LocalDate object.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Class constructor specifying the description of the deadline task. It is called when data is loaded from the
     * storage.
     * @param description the string description of the task.
     * @param by the deadline of the task as a String.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string description which contains the deadline of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline task = (Deadline) obj;
        int indexOfTaskDescription = 7;
        if (this.toString().substring(indexOfTaskDescription).equals(
                task.toString().substring(indexOfTaskDescription))) {
            return true;
        }
        return false;
    }

    /**
     * Returns the information associated with the deadline to be stored on local hard disk.
     * @return the string representation of the deadline saved onto the local hard disk.
     */
    public String saveTask() {
        return "D|" + (this.isDone() ? "X|" : " |") + this.getDescription() + "|" + this.by;
    }
}

