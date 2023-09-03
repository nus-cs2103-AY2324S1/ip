package nexus.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("2103 tut", LocalDateTime.of(2023, Month.JANUARY, 15, 20, 30));
        deadline.setDone();
        assertEquals("[D][X] 2103 tut (by: 15 Jan 2023 2030)", deadline.toString());
    }

    @Test
    public void testTodoStorage() {
        Deadline deadline = new Deadline("2103 tut", LocalDateTime.of(2023, Month.JANUARY, 15, 20, 30));
        deadline.setDone();
        assertEquals("D|1|2103 tut|15/1/2023 2030", deadline.toStorageString());
    }
}
