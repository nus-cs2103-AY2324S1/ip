import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class TaskDeserializer extends StdDeserializer<Task> {
    public TaskDeserializer() {
        this(null);
    }

    public TaskDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Task deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        
        JsonNode taskTypeNode = node.get("type");
        String taskType = taskTypeNode.asText();

        JsonNode taskNameNode = node.get("name");
        String taskName = taskNameNode.asText();

        Task task;
        switch (taskType) {
        case "todo":
            task = new Todo(taskName);
            break;
        case "deadline":
            JsonNode deadlineNode = node.get("deadline");
            String deadline = deadlineNode.asText();
            task = new Deadline(taskName, deadline);
            break;
        case "event":
            JsonNode startNode = node.get("start");
            String start = startNode.asText();
            JsonNode endNode = node.get("end");
            String end = endNode.asText();
            task = new Event(taskName, start, end);
            break;
        default:
            task = new Task(taskName);
            break;
        }

        JsonNode completionStageNode = node.get("isCompleted");
        Boolean isCompleted = completionStageNode.asBoolean();

        if (isCompleted) {
            task.mark();
        }
        

        return task;
    }
}
