import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TaskSerializer extends StdSerializer<Task> {
    public TaskSerializer() {
        this(null);
    }

    public TaskSerializer(Class<Task> t) {
        super(t);
    }

    @Override
    public void serialize(
      Task task, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        task.serialize(jsonGenerator, serializer);
    }
}
