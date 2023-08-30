package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testTaskStringify() {
        // isDone is false
        assertEquals("D | 0 | return book | 2023-06-06 1800",
                new Deadline("return book", false, "2023-06-06 1800").taskStringify());

        // isDone is true
        assertEquals("D | 1 | return book | 2023-06-06 1800",
                new Deadline("return book", true, "2023-06-06 1800").taskStringify());
    }

    @Test
    public void testToString() {
        // isDone not given
        assertEquals("[D][ ] return book (by: Jun 06 2023 1800)",
                new Deadline("return book", "2023-06-06 1800").toString());

        // isDone is false
        assertEquals("[D][ ] return book (by: Jun 06 2023 1800)",
                new Deadline("return book", false, "2023-06-06 1800").toString());

        // isDone is true
        assertEquals("[D][X] return book (by: Jun 06 2023 1800)",
                new Deadline("return book", true, "2023-06-06 1800").toString());
    }
}
