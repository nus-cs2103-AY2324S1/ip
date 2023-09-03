package duke.task;

import java.io.FileWriter;
import java.io.IOException;

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
    public Event(String name, String from, String to) {
        super(name);
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
    public Event(String name, String from, String to, boolean isComplete) {
        super(name, isComplete);
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
        Event eventTask = (Event) task;
        return this.to.equals(eventTask.getTo()) && this.getName().equals(eventTask.getName())
                && this.from.equals(eventTask.getFrom());
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
