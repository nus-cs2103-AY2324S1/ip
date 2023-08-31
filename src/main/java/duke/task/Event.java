package duke.task;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Duke application.
 * Events have a start and end time.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs an Event task with the given task description, completion status, start time, and end time.
     *
     * @param task The description of the event task.
     * @param isDone The completion status of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @throws DateTimeException If there is an issue with date or time formatting.
     */
    public Event(String task, boolean isDone, String from, String to) throws DateTimeException {
        super(task, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "E|" + (this.isDone ? "1|" : "0|") + this.getTask() + "|" + this.from + "|" + this.to;
        fw.write(storedRow);
    }

}
