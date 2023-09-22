package duke; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Class for testing the Event Class
 */
public class EventTest {
    @Test
    public void outputTest() {
        Event event = new Event("Meeting", "2023-09-13 09:00", "2023-09-13 10:00");
        assertEquals("[E][ ] Meeting (from: 2023-09-13 09:00 to: 2023-09-13 10:00)", event.toString());
    }

    @Test
    public void saveStringTest() {
        Event event = new Event("Meeting", "2023-09-13 09:00", "2023-09-13 10:00");
        assertEquals("E | 0 | Meeting | 2023-09-13 09:00 | 2023-09-13 10:00", event.toSaveString());
    }

}
