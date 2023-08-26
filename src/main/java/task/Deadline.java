package task;

import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the Deadline Task.
 * @author Donovan Chan Jia Jun
 */
public class Deadline extends Task {
    private String deadline;

    private String getDeadline() {
        return this.deadline;
    }
    public Deadline(String name, String deadline) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            deadline = dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {

        }
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline, boolean isComplete) {
        super(name, isComplete);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            deadline = dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {

        }
        this.deadline = deadline;
    }

    /**
     * Checks if task and another object are equal.
     *
     * @param task Task to be compared against
     * @return {@code true} if both tasks are equal
     */
    @Override
    public boolean equals(Object task) {
        if (this == task) {
            return true;
        }
        Deadline deadlineTask = (Deadline) task;
        return this.deadline.equals(deadlineTask.getDeadline())
                && this.getName().equals(deadlineTask.getName());
    }

    /**
     * Write the Deadline object to the storage file in its format.
     *
     * @param fileWriter Filewriter that writes to a specific output file
     */
    public void writeToFile(FileWriter fileWriter) {
        String marking = super.isComplete() ? "0" : "1";
        try {
            fileWriter.write("D" + "|" + marking + "|" + super.getName() + "|" + this.deadline);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the String representation of the Deadline object.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getMarking(), super.name, this.deadline);
    }
}
