package Frenchie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a Deadline that is stored in the Task list of the Frenchie chatbot.
 * <p>
 * The ToDo class inherits the 2 attributes from the Task class with an additional
 * LocalDateTime attribute deadline. The deadline attribute represents the deadline
 * by which the Deadline task has to be completed.
 * <p>
 */
public class Deadline extends Task {
    LocalDateTime deadline;

    /**
     *  Constructs a new Deadline object, with a default false value for isCompleted as tasks inputted into the task list are incomplete.
     *  Takes in a String name which is the name of the task as well as a String deadline in the format "dd/MM/yyyy HH:mm".
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.isCompleted = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     *  Overrides the toString() method inherited from the Task class.
     *  [D] to indicate that the task is a deadline.
     *  Deadline is formatted in 'yyyy-MM-dd HH:mm' to output.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String desiredFormat = this.deadline.format(formatter);
        return "[D]" + super.toString() + "(by: " + desiredFormat + ")";
    }
}


