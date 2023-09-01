package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventsTest {

    private Events event;

    @BeforeEach
    public void setUp() {
        event = new Events("Meeting", "2023/09/01 1400", "2023/09/01 1500");
    }

    @Test
    public void testEventCreation() {
        assertEquals("[E][ ] Meeting (from: 09-1-2023 14:00, to: 09-1-2023 15:00)", event.toString());
    }

    @Test
    public void testGetSavingFormat() {
        assertEquals("[E] | [ ] | Meeting | 2023/09/01 1400 | 2023/09/01 1500", event.getSavingFormat());
    }

    @Test
    public void testEventCreationWithInvalidStartDate() {
        assertThrows(DateTimeParseException.class, () -> new Events("Meeting", "2023-09-01", "2023/09/01 1500"));
    }

    @Test
    public void testEventCreationWithInvalidEndDate() {
        assertThrows(DateTimeParseException.class, () -> new Events("Meeting", "2023/09/01 1400", "2023-09-01"));
    }
}
