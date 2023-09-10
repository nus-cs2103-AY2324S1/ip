import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Deadline is the main class for Deadline task used by the Sidtacphi bot.
 */
public class Deadline extends Task {
    private String deadline = "";

    /**
     * Constructor for the Deadline class.
     * 
     * @param name
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
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
        jsonGenerator.writeStringField("deadline", deadline);
        jsonGenerator.writeEndObject();
    }
}
