package sidtacphi.task;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Todo is the main class for Todo task used by the Sidtacphi bot.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Shows whether the task is completed and the name of the task.
     *
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Seralizes Todo to be stored in Json format.
     */
    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "todo");
        jsonGenerator.writeStringField("name", super.getName());
        jsonGenerator.writeBooleanField("isCompleted", super.isCompleted());
        jsonGenerator.writeEndObject();
    }

    /**
     * Checks if obj is equal to to the Todo object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        Todo task = (Todo) obj;
        return Objects.equals(task.getName(), super.getName()) && task.isCompleted() == super.isCompleted();
    }
}
