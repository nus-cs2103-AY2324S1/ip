package peko.tasks;

import peko.DateTimeHandler;
import peko.exceptions.InvalidTaskException;


/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and includes additional functionality for handling deadlines.
 */
public class Deadline extends Task {
    char type =  'D';
    DateTimeHandler dateTimeHandler;

    /**
     * Constructs a Deadline object with the specified description.
     *
     * @param s The description of the deadline task, including the task name and deadline.
     * @throws InvalidTaskException If the provided description is invalid or missing essential parts.
     */
    public Deadline(String s) throws InvalidTaskException {
        super(s);
        String[] split = s.split(" /by ");
        if (split.length == 1) {
            System.out.println("There's no deadline peko!");
            return;
        } else if (split.length >= 3){
            System.out.println("You can't have two deadlines peko!");
            return;
        }

        String[] temp = split[0].split(" ",2);
        //System.out.println(Arrays.toString(temp));

        this.name = temp[0];

        dateTimeHandler = new DateTimeHandler(split[1]);
    }

    /**
     * Converts the Deadline object to a formatted string representation.
     *
     * @return A formatted string representing the deadline task, including its type, status,
     *         task name, and deadline.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: "  + dateTimeHandler.stringDisplay() + ")";
    }

    /**
     * Converts the Deadline object to a string for storage in a text file.
     *
     * @return A string representation of the deadline task suitable for storage in a text file.
     */
    public String toStore() {
        String state = this.status ? "0" : "1";
        String output = "D" + " | " + state + " | " + this.name + " | " + dateTimeHandler.toString();
        return output;
    }
}
