package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testMarkAsDone() {
        Deadline deadline = new Deadline("do math", LocalDateTime.parse("20-05-2013 00:00",
                DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm")));
        deadline.markAsDone();
        assertEquals(true, deadline.isDone);
    }

    @Test
    public void testMarkAsUndone() {
        Deadline deadline = new Deadline("do science", LocalDateTime.parse("20-05-2014 00:00",
                DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm")));
        deadline.markAsDone();
        deadline.markAsUndone();
        assertEquals(false, deadline.isDone);
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("do math", LocalDateTime.parse("20-05-2013 00:00",
                DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm")));
        assertEquals("[D][ ] do math (by: May 20 2013 00:00)", deadline.toString());
    }

    @Test
    public void testStorageFormat() {
        Deadline deadline = new Deadline("do math", LocalDateTime.parse("20-05-2013 00:00",
                DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm")));
        assertEquals("D | 0 | do math | 2013-05-20T00:00", deadline.storageFormat());
    }

}