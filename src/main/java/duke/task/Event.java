package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the task.Events Task.
 * @author Donovan Chan Jia Jun
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs Event Object.
     *
     * @param name String representation for the name of the event
     * @param from String representation for when the event starts
     * @param to String representation for when the event ends
     */
    public Event(String name, String from, String to) throws Exception {
        super(name);
        if (!Event.isValidDates(from, to)) {
            throw new Exception("Event start date should be before the end date!");
        }
        this.to = to;
        this.from = from;
    }

    /**
     * Constructs Event Object.
     *
     * @param name name String representation for the name of the event
     * @param from String representation for when the event starts
     * @param to String representation for when the event ends
     * @param isComplete Boolean value to show if Event has been marked as complete
     */
    public Event(String name, String from, String to, boolean isComplete) throws Exception {
        super(name, isComplete);
        if (!Event.isValidDates(from, to)) {
            throw new Exception("Event start date should be before the end date!");
        }
        this.to = to;
        this.from = from;
    }

    private String getTo() {
        return this.to;
    }

    private String getFrom() {
        return this.from;
    }

    /**
     * Verifies if the dates, from is before to.
     * Dates not in "d/M/yyyy HHmm" format are accepted automatically
     *
     * @param from String representation of the Event start datetime
     * @param to String representation of the Event end datetime
     * @return true if valid start and end datetime, otherwise false
     */
    private static boolean isValidDates(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime fromDate = LocalDateTime.parse(from, formatter);
            LocalDateTime toDate = LocalDateTime.parse(to, formatter);
            if (fromDate.isBefore(toDate)) {
                return true;
            }
        } catch (DateTimeParseException e) {
            // The dates are not of the date format
            return true;
        }
        return false;
    }

    /**
     * Checks if both objects are equal.
     *
     * @param task Task object to be compared agianst
     * @return {@code true} if both objects are equal
     */
    @Override
    public boolean equals(Object task) {
        if (this == task) {
            return true;
        }
        if (task instanceof Event) {
            Event eventTask = (Event) task;
            return this.to.equals(eventTask.getTo()) && this.getName().equals(eventTask.getName())
                    && this.from.equals(eventTask.getFrom());
        }
        return false;
    }

    /**
     * Write the Event object to the storage file in its format.
     *
     * @param fileWriter Filewriter that writes to a specific output file
     */
    public void writeToFile(FileWriter fileWriter) {
        String marking = super.isComplete() ? "0" : "1";
        try {
            fileWriter.write("E" + "|" + marking + "|" + super.getName() + "|" + this.from + "|" + this.to);
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the String representation of the task.Events object.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)", super.getMarking(), super.name, this.from, this.to);
    }
}
