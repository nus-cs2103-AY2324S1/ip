package duke;

import gman.Deadline;
import org.junit.jupiter.api.Test;
public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline deadline  = new Deadline(" have more fun", "2019-10-19");
        assert deadline.toString().equals("[D][ ] have more fun (by: Oct 19 2019)");
    }
}
