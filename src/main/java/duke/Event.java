package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Event` class represents a task with a specific start and end date/time.
 * It is a subclass of the `Task` class and includes information about
 * the event's timing.
 */
public class Event extends Task{

    private String fromStr;
    private String toStr;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initializes a new `Event` object with the given task description, save status, start date/time, and end date/time.
     *
     * @param task The description of the event task.
     * @param isNotSaved A boolean indicating whether the task needs to be saved to a file.
     * @param from The start date/time of the event in string format (dd/MM/yyyy HHmm).
     * @param to The end date/time of the event in string format (dd/MM/yyyy HHmm).
     * @throws DukeException If the date/time format is invalid.
     */
    public Event(String task, Boolean isNotSaved, String from, String to) throws DukeException {
        super(task, isNotSaved);
        this.fromStr = from;
        this.toStr = to;
        try {
            this.from = parseDateTime(from);
            this.to = parseDateTime(to);
        } catch (Exception e) {
            throw new DukeException(Ui.horizontalLine
                    + "Invalid date format :< Please use dd/MM/yyyy\n" + Ui.horizontalLine);
        }

        if (isNotSaved) {
            saveToFile();
        }
    }

    /**
     * Returns a string representation of the `Event` object.
     *
     * @return A formatted string representing the event task with its start and end date/time.
     */
    @Override
    public String toString() {
        String formattedFromDate = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedFromTime = from.format(DateTimeFormatter.ofPattern("hh:mma"));
        String formattedToDate = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedToTime = to.format(DateTimeFormatter.ofPattern("hh:mma"));

        return "[E]" + super.toString() + " (from: " + formattedFromDate + " " + formattedFromTime
                + " to: " + formattedToDate + " " + formattedToTime + ")";
    }

    /**
     * Prints a confirmation message after adding the `Event` task.
     */
    public void print() {
        System.out.println(Ui.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Ui.horizontalLine);
    }

    /**
     * Generates a string representation of the `Event` task for saving to a file.
     *
     * @return A formatted string for saving the event task to a file.
     */
    public String generateStr() {
        return "E | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask() + " | " + fromStr + " | " + toStr;
    }

    /**
     * Saves the `Event` task to a file.
     */
    @Override
    public void saveToFile() {
        Storage.saveTaskToFile(generateStr());
    }

}
