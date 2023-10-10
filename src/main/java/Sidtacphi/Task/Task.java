package sidtacphi.task;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Task is the main class for tasks used by the Sidtacphi bot.
 */
public class Task {
    private String name = "";
    private boolean isCompleted = false;

    /**
     * Constructor for the Task class.
     *
     * @param name Name of the class
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     *
     * @return Completion state of task and the name of task.
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Gets the name of the task.
     *
     * @return Name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * @return Completed state of the task
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Seralizes Task to be stored in Json format.
     */
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "task");
        jsonGenerator.writeStringField("name", name);
        jsonGenerator.writeBooleanField("isCompleted", isCompleted);
        jsonGenerator.writeEndObject();
    }

    /**
     * Checks if obj is equal to to the Task object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return Objects.equals(task.getName(), name) && task.isCompleted() == isCompleted;
    }
}
