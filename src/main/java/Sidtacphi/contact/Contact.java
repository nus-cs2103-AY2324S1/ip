package sidtacphi.contact;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Contact is the main class for contact tracking by the Sidtacphi bot.
 */
public class Contact {
    private String name = "";
    private String desc = "";

    /**
     * Constructs a Contact object.
     *
     * @param name
     */
    public Contact(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * Gets the name of the contact.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the contact.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     *
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[C] " + name + " (Description: " + desc + ")";
    }

    /**
     * Seralizes Deadline to be stored in Json format.
     */
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", name);
        jsonGenerator.writeStringField("description", desc);
        jsonGenerator.writeEndObject();
    }

    /**
     * Checks if obj is equal to to the Deadline object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) obj;
        return Objects.equals(contact.getName(), name)
                && Objects.equals(contact.getDesc(), desc);
    }
}
