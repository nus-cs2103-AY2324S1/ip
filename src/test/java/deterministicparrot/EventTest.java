package deterministicparrot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void createEvent_validInput_success() {
        Event event = new Event("Birthday Party", "2023-09-02", "2023-09-02");
        assertEquals("[E][ ] Birthday Party (from: Sep 2 2023 to: Sep 2 2023)", event.toString());
    }

    @Test
    public void createEvent_invalidTimeStartFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Event event = new Event("Birthday Party", "02-09-2023", "2023-09-02");
        });
    }

    @Test
    public void createEvent_invalidTimeEndFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Event event = new Event("Birthday Party", "2023-09-02", "02-09-2023");
        });
    }

    @Test
    public void createEvent_timeStartAfterTimeEnd_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Event event = new Event("Birthday Party", "2023-09-03", "2023-09-02");
        });
    }

}
