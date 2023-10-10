package sidtacphi.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Deadline is the main class for Deadline task used by the Sidtacphi bot.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructs a Deadline object.
     *
     * @param name
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the object.
     *
     * @return Deadline of Deadline object
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     *
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Seralizes Deadline to be stored in Json format.
     */
    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "deadline");
        jsonGenerator.writeStringField("name", super.getName());
        jsonGenerator.writeBooleanField("isCompleted", super.isCompleted());
        jsonGenerator.writeStringField("deadline", deadline.toString());
        jsonGenerator.writeEndObject();
    }

    /**
     * Checks if obj is equal to to the Deadline object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline task = (Deadline) obj;
        return Objects.equals(task.getName(), super.getName()) && task.isCompleted() == super.isCompleted()
                && Objects.equals(deadline, task.getDeadline());
    }
}
