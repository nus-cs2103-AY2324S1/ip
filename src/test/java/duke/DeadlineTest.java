package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_toString_success() {
        Deadline d = new Deadline("test",
                LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                        LocalTime.of(23, 59)));
        assertEquals("[D][ ] test (by: Sun, 01 Jan 2023 23:59)",
                d.toString());
    }

    @Test
    public void deadline_completed_toString_success() {
        Deadline d = new Deadline("test", true,
                LocalDateTime.of(LocalDate.of(2023, Month.JANUARY, 1),
                        LocalTime.of(23, 59)));
        assertEquals("[D][X] test (by: Sun, 01 Jan 2023 23:59)",
                d.toString());
    }
}
