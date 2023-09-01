package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void mark_success() throws Exception {
        assertEquals("[D][X] return book (by: 2019-Oct-14 10:30:35)",
                new Deadline("return book", "2019-10-14 10:30:35")
                        .mark().toString());
    }

    @Test
    public void unmark_success() throws Exception {
        assertEquals("[D][ ] return book (by: 2019-Oct-14 10:30:35)",
                new Deadline("return book", "2019-10-14 10:30:35")
                        .unmark().toString());
    }

    @Test
    public void saveTask_success() throws Exception {
        assertEquals("D | 0 | borrow book | 2019-10-14 10:30:35",
                new Deadline("borrow book", "2019-10-14 10:30:35").saveTask());
    }
}
