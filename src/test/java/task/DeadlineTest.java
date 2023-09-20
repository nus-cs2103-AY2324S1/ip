package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void mark_success() throws Exception {
        Deadline deadline = new Deadline("return book", "2019-10-14 10:30:35");
        Deadline markedDeadline = deadline.mark();
        assertEquals("[D][X] return book (by: 2019-Oct-14 10:30:35)", markedDeadline.toString());
    }

    @Test
    public void unmark_success() throws Exception {
        Deadline deadline = new Deadline("return book", "2019-10-14 10:30:35");
        Deadline unmarkedDeadline = deadline.unmark();
        assertEquals("[D][ ] return book (by: 2019-Oct-14 10:30:35)", unmarkedDeadline.toString());
    }

    @Test
    public void saveTask_success() throws Exception {
        Deadline deadline = new Deadline("borrow book", "2019-10-14 10:30:35");
        String savedTask = deadline.saveTask();
        assertEquals("D | 0 | borrow book | 2019-10-14 10:30:35", savedTask);
    }
}
