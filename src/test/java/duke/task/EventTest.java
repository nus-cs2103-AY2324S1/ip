package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testEventStringConversion() throws DukeException {
        assertEquals(
                "[E][ ] Malaysia Trip (from: Aug 12 2023 to: Aug 13 2023)",
                new Event("Malaysia Trip", "2023-08-12", "2023-08-13").toString()
        );

        assertEquals(
                "[E][ ] Camp (from: Sept 11 2023 to: Sept 14 2023)",
                new Event("Camp", "2023-09-11", "2023-09-14").toString()
        );

        assertEquals(
                "[E][ ] Birthday Party (from: Oct 15 2023 to: Oct 16 2023)",
                new Event("Birthday Party", "2023-10-15", "2023-10-16").toString()
        );
    }

    @Test
    public void testConstructor_invalidDates_exceptionThrown() {
        try {
            assertEquals(
                    "[E][ ] Graduation Trip (from: May 10 2024 to: May 9 2024)",
                    new Event("Graduation Trip", "2024-05-10", "2024-05-09").toString()
            );
        } catch (Exception e) {
            assertEquals("Start date cannot be after end date", e.getMessage());
        }
    }

}
