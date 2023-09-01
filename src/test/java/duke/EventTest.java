package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {


    @Test
    public void event_toString_success() {
        Event e = new Event("test",
                LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                        LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                        LocalTime.of(23, 59)));
        assertEquals("[E][ ] test (from: 01 Jan 2023 00:00 to: 01 Jan 2023 23:59)",
                e.toString());
    }

    @Test
    public void event_completed_toString_success() {
        Event e = new Event("test", true,
                LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                        LocalTime.of(0, 0)),
                LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                        LocalTime.of(23, 59)));
        assertEquals("[E][X] test (from: 01 Jan 2023 00:00 to: 01 Jan 2023 23:59)",
                e.toString());
    }

}
