package Sidtacphi.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Event is the main class for Event task used by the Sidtacphi bot.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructor for the Event class.
     * 
     * @param name
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     * 
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + ", to: " + end + ")";     
    }

    /**
     * Seralizes Event to be stored in Json format.
     */
    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "event");
        jsonGenerator.writeStringField("name", super.getName());
        jsonGenerator.writeBooleanField("isCompleted", super.isCompleted());
        jsonGenerator.writeStringField("start", start.toString());
        jsonGenerator.writeStringField("end", end.toString());
        jsonGenerator.writeEndObject();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        } 
        Event task = (Event) obj;
        return Objects.equals(task.getName(), super.getName()) && task.isCompleted() == super.isCompleted() 
                && Objects.equals(task.getStart(), start) && Objects.equals(task.getEnd(), end);
    }
}
