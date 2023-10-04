package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest() {
        Event event = new Event("orientation week", LocalDate.parse("2023-07-31"),
                LocalDate.parse("2023-08-04"));
        assertEquals("[E] [ ] orientation week (from: Jul 31 2023 to: Aug 04 2023)", event.toString());

        event.markDone();
        assertEquals("[E] [X] orientation week (from: Jul 31 2023 to: Aug 04 2023)", event.toString());

        event.markNotDone();
        assertEquals("[E] [ ] orientation week (from: Jul 31 2023 to: Aug 04 2023)", event.toString());
    }
}
