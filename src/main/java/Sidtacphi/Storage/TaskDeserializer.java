package Sidtacphi.Storage;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import Sidtacphi.Task.Deadline;
import Sidtacphi.Task.Event;
import Sidtacphi.Task.Task;
import Sidtacphi.Task.Todo;

public class TaskDeserializer extends StdDeserializer<Task> {
    /**
     * Constructs TaskDeserializer object.
     */
    public TaskDeserializer() {
        this(null);
    }

    /**
     * Constructs TaskDeserializer object.
     */
    public TaskDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserializes a Task object.
     * 
     * @return Task object after deserialising
     */
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
            task = new Deadline(taskName, LocalDate.parse(deadline));
            break;
        case "event":
            JsonNode startNode = node.get("start");
            String start = startNode.asText();
            JsonNode endNode = node.get("end");
            String end = endNode.asText();
            task = new Event(taskName, LocalDate.parse(start), LocalDate.parse(end));
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
