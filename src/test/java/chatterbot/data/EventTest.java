package chatterbot.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void event_description_addedToList() {
        // entered event will be added successfully
        assertEquals("[E][ ] birthday party (from: today to: tomorrow)", new Event("birthday party ","today ", "tomorrow").toString());

        // entered event will be added successfully
        assertEquals("[E][ ] training (from: morning to: evening)", new Event("training ", "morning ", "evening").toString());
    }
}
