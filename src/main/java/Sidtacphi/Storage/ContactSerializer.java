package sidtacphi.storage;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import sidtacphi.contact.Contact;

/**
 * TaskSerializer is the class that serializes a Task object.
 */
public class ContactSerializer extends StdSerializer<Contact> {
    /**
     * Constructs TaskSerializer object.
     */
    public ContactSerializer() {
        this(null);
    }

    /**
     * Constructs TaskSerializer object.
     */
    public ContactSerializer(Class<Contact> t) {
        super(t);
    }

    /**
     * Serializes a Task object.
     */
    @Override
    public void serialize(
        Contact contact, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        contact.serialize(jsonGenerator, serializer);
    }
}
