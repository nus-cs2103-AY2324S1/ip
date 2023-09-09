package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Deadline` class represents a task with a specific deadline in the Duke program.
 * It is a subclass of the `Task` class and provides functionality to handle tasks with deadlines.
 */
public class Deadline extends Task {

    private String byStr;
    private LocalDateTime by;

    /**
     * Initializes a new `Deadline` task with the specified description and deadline.
     *
     * @param task The description of the task.
     * @param isNotSaved A boolean indicating whether the task needs to be saved.
     * @param by The deadline of the task in string format (dd/MM/yyyy).
     * @throws DukeException If there is an issue parsing the deadline format.
     */
    public Deadline(String task, Boolean isNotSaved, String by) throws DukeException {
        super(task, isNotSaved);
        try {
            this.by = parseDateTime(by);
            this.byStr = by;

        } catch (Exception e) {
            throw new DukeException(Ui.horizontalLine + "Invalid date format :< Please use dd/MM/yyyy\n" + Ui.horizontalLine);
        }
        if (isNotSaved) {
            saveToFile();
        }
    }

    /**
     * Returns a string representation of the `Deadline` task, including its description and deadline.
     *
     * @return A formatted string representing the `Deadline` task.
     */
    public String toString() {
        // Format LocalDateTime as a string in your desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String formattedDateTime = by.format(outputFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }

    /**
     * Prints a message to confirm that the task has been added and displays the updated task count.
     */
    public void print() {
        System.out.println(Ui.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Ui.horizontalLine);
    }

    /**
     * Generates a string representation of the `Deadline` task for saving to a file.
     *
     * @return A formatted string representing the `Deadline` task for file storage.
     */
    public String generateStr() {
        return "D | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask() + " | " + byStr;
    }

    /**
     * Saves the `Deadline` task to a file if it has not been saved already.
     */
    @Override
    public void saveToFile() {
        if (isNotSaved) {
            Storage.saveTaskToFile(generateStr());
        }
    }

}
