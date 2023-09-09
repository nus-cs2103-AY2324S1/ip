package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class EventsTest {
    @Test
    public void testToString() {
        Events event = new Events("Conference", LocalDate.parse("2023-09-15"), LocalDate.parse("2023-09-17"));
        String expectedString = "[E][ ] Conference(from: Sep 15 2023 to: Sep 17 2023)";

        assertEquals(expectedString, event.toString());
    }

    @Test
    public void testSave() {
        Events event = new Events("Workshop", LocalDate.parse("2023-08-20"), LocalDate.parse("2023-08-21"));
        String expectedSaveString = "E|0|Workshop|2023-08-20|2023-08-21";

        assertEquals(expectedSaveString, event.save());
    }
}
