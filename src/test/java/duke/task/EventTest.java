package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void EventTest() {
        Event event = new Event("orientation week", LocalDate.parse("2023-07-31"),
                LocalDate.parse("2023-08-04"));
        assertEquals("[E] [ ] orientation week (from: Jul 31 2023 to: Aug 04 2023)", event.toString());

        event.markDone();
        assertEquals("[E] [X] orientation week (from: Jul 31 2023 to: Aug 04 2023)", event.toString());

        event.markNotDone();
        assertEquals("[E] [ ] orientation week (from: Jul 31 2023 to: Aug 04 2023)", event.toString());
    }
}
