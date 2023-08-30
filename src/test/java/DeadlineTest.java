package test.java;

import main.java.Deadline;
import main.java.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void deadline_markTest_success() {
        Task t = new Deadline("test 1", "2019-10-15");
        t.markDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    public void deadline_unmarkTest_success() {
        Task t = new Deadline("test 1", "2019-10-15", true);
        t.markUndone();
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void deadline_printString_correctFormat() {
        Task t = new Deadline("test 3", "2019-10-15");
        assertEquals("[D][ ] test 3 (by: Oct 15 2019)", t.toString());
    }
}
