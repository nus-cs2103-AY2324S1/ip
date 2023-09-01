package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getByTest() {
        LocalDate date = LocalDate.now();
        Deadline deadline = new Deadline("Sample Deadline", date);
        assertEquals(date, deadline.getBy());
    }

    @Test
    void testToStringTest() {
        LocalDate date = LocalDate.now();
        Deadline deadline = new Deadline("Sample Deadline", date);
        assertEquals("[D][ ] Sample Deadline (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")", deadline.toString());

        deadline.markAsDone();
        assertEquals("[D][X] Sample Deadline (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")", deadline.toString());
    }
}