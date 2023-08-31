package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;


public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("CS2103T", LocalDate.of(2023, 8, 31), LocalTime.of(13, 45));
        assertEquals("[D][ ] CS2103T (by 31 Aug 2023 | 1.45PM)", deadline.toString());
    }

    @Test
    public void testFileDescription() {
        Deadline deadline = new Deadline("CS2103T", LocalDate.of(2023, 8, 31), LocalTime.of(13, 45));
        assertEquals("D | 1 | CS2103T | 31 Aug 2023 | 1.45PM" + "\n", deadline.fileDescription());

        deadline.markAsDone();
        assertEquals("D | 0 | CS2103T | 31 Aug 2023 | 1.45PM" + "\n", deadline.fileDescription());
    }

}
