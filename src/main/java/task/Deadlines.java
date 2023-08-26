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
public class Deadlines extends Task {
    private String deadline;

    private String getDeadline() {
        return this.deadline;
    }
    public Deadlines(String name, String deadline) {
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

    public Deadlines(String name, String deadline, boolean isComplete) {
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

    @Override
    public boolean equals(Object task) {
        if (this == task) {
            return true;
        }
        Deadlines deadlineTask = (Deadlines) task;
        return this.deadline.equals(deadlineTask.getDeadline())
                && this.getName().equals(deadlineTask.getName());
    }

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
