package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void toString_validDate_success() {
        try {
            Task event = new Event("Supernova", "2024-04-05 1900", "2024-04-05 2200");
            assertEquals("[E][ ] Supernova (from: Apr 05 2024 19:00 to: Apr 05 2024 22:00)",
                    event.toString());
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void toDataString_validFormat_success() {
        Task event = new Event("Supernova", "2024-04-05 1900", "2024-04-05 2200");
        assertEquals("E / 0 / Supernova / 2024-04-05 1900 / 2024-04-05 2200", event.toDataString());
    }

}
