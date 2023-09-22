package sam;

import org.junit.jupiter.api.Test;
import sam.tasks.Deadline;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void toString_savedInTaskList_correctFormat() {
        Deadline deadline = new Deadline("Return books", LocalDateTime.of(2023, 11,
                15, 8, 0));
        assertEquals(deadline.toString(), "[D][ ] Return books (by: Nov 15 2023 08:00 am)");
    }

    @Test
    public void toFileString_savedInHardDisk_correctFormat() {
        Deadline deadline = new Deadline("Return books", LocalDateTime.of(2023, 11,
                15, 8, 0));
        assertEquals(deadline.toFileString(), "D | 0 | Return books | 2023-11-15T08:00");
    }

    @Test
    public void markTask_toString_savedInTaskList_correctFormat() {
        Deadline deadline = new Deadline("Return books", LocalDateTime.of(2023, 11,
                15, 8, 0));
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] Return books (by: Nov 15 2023 08:00 am)");
    }

    @Test
    public void markTask_toFileString_savedInHardDisk_correctFormat() {
        Deadline deadline = new Deadline("Return books", LocalDateTime.of(2023, 11,
                15, 8, 0));
        deadline.markAsDone();
        assertEquals(deadline.toFileString(), "D | 1 | Return books | 2023-11-15T08:00");
    }
}
