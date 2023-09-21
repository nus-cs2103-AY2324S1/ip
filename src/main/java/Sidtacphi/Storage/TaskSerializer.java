package Sidtacphi.Storage;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import Sidtacphi.Task.Task;

/**
 * TaskSerializer is the class that serializes a Task object.
 */
public class TaskSerializer extends StdSerializer<Task> {
    /**
     * Constructs TaskSerializer object.
     */
    public TaskSerializer() {
        this(null);
    }

    /**
     * Constructs TaskSerializer object.
     */
    public TaskSerializer(Class<Task> t) {
        super(t);
    }

    /**
     * Serializes a Task object.
     */
    @Override
    public void serialize(
      Task task, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        task.serialize(jsonGenerator, serializer);
    }
}
