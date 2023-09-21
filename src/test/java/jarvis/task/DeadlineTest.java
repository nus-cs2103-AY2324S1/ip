package jarvis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", LocalDateTime.of(2023, 9, 20, 12, 0));
        assertEquals("[D][ ] return book (by: Sep 20 2023 1200)", deadline.toString());
    }

    @Test
    public void testToSaveString() {
        Deadline deadline = new Deadline("return book", false, LocalDateTime.of(2023, 9, 20, 12, 0));
        assertEquals("D | 0 | return book | Sep 20 2023 1200", deadline.toSaveString());
    }

    @Test
    public void testToSaveStringCompleted() {
        Deadline deadline = new Deadline("return book", true, LocalDateTime.of(2023, 9, 20, 12, 0));
        assertEquals("D | 1 | return book | Sep 20 2023 1200", deadline.toSaveString());
    }

    @Test
    public void testGetByString() {
        Deadline deadline = new Deadline("return book", LocalDateTime.of(2023, 9, 20, 12, 0));
        assertEquals("Sep 20 2023 1200", deadline.getByString());
    }
}
