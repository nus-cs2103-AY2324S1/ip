package chatterbot.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void event_description_addedToList() {
        // entered event will be added successfully
        assertEquals("[E][ ] birthday party (from: today to: tomorrow)", new Event("birthday party ","today ", "tomorrow").toString());

        // entered event will be added successfully
        assertEquals("[E][ ] training (from: morning to: evening)", new Event("training ", "morning ", "evening").toString());
    }

    @Test
    public void event_empty_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("", "", "");
        });
    }
}
