package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a deadline task for the Duke program.
 * It extends the SingleTask class and provides additional methods specific to deadline tasks.
 */
public class Deadline extends SingleTask {
    String formattedTime;
    LocalDateTime DueBy;
    String unformattedTime;

    /**
     * Constructs a new Deadline object with the given description and deadline.
     * @param description the description of the Deadline task
     * @param deadline the deadline of the Deadline task in the format "dd/MM/yyyy HHmm"
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.unformattedTime = deadline;
        String[] parts = deadline.split("[/ ]");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3].substring(0, 2));
        int minute = Integer.parseInt(parts[3].substring(2));
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
        this.DueBy = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        this.formattedTime = formattedDateTime;

    }

    /**
     * Marks the task as done and displays a message to the user.
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Ok boy i mark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);

    }

    /**
     * Returns the status icon of the task.
     * @return a string representing the status icon of the task
     */
    public String getStatusIcon() {

        return (this.isDone ? "X" : " ");
    }

    /**
     * Unmarks the task as done and displays a message to the user.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok boy I unmark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }

    /**
     * Returns a string representation of the task when it is added to a list.
     * @return a string representing the task when it is added
     */
    @Override
    public String toString() {
        return "OK DONE ALR added your Deadline ah:\n" +
                "[D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.formattedTime + ")";
    }
    /**
     * Returns a string representation of the task for displaying in a list.
     * @return a string representing the task in a list
     */
    @Override
    public String listString() {
        return ". [D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.formattedTime + ")";
    }

    /**
     * Returns a string representation of the task when it is removed from a list.
     * @return a string representing the task when it is removed
     */
    @Override
    public String remove() {
        return "OK DONE ALR removed your Deadline ah:\n" +
                "[D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.formattedTime + ")";
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * @return a string representing the task in a save file
     */
    @Override
    public String toSaveString() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.unformattedTime;
    }
}
