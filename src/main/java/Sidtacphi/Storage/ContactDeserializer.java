package sidtacphi.storage;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import sidtacphi.contact.Contact;

/**
 * ContactDeserializer is the class that deserializes a Contact object.
 */
public class ContactDeserializer extends StdDeserializer<Contact> {
    /**
     * Constructs ContactDeserializer object.
     */
    public ContactDeserializer() {
        this(null);
    }

    /**
     * Constructs ContactDeserializer object.
     */
    public ContactDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserializes a Contact object.
     *
     * @return Task object after deserialising
     */
    @Override
    public Contact deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode nameNode = node.get("name");
        String name = nameNode.asText();

        JsonNode descNode = node.get("description");
        String desc = descNode.asText();

        return new Contact(name, desc);
    }
}
