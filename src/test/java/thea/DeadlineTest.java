package thea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toMemoryFormatTest() {
        Deadline deadline = new Deadline("CS2103 Week 4 ip", "2023-09-07 23:59");
        assertEquals("D | 0 | CS2103 Week 4 ip | 2023-09-07 23:59",
                deadline.toMemoryFormat());
    }

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("CS2103 Week 4 ip", "2023-09-07 23:59");
        assertEquals("[D][ ] CS2103 Week 4 ip (by: Sep 7 2023 23:59)",
                deadline.toString());
    }

    @Test
    public void markAsDoneTest() {
        Deadline deadline = new Deadline("CS2103 Week 4 ip", "2023-09-07 23:59");
        deadline.markAsDone();
        assertEquals("[D][X] CS2103 Week 4 ip (by: Sep 7 2023 23:59)",
                deadline.toString());
    }

    @Test
    public void unmarkAsDoneTest() {
        Deadline deadline = new Deadline("CS2103 Week 4 ip", "2023-09-07 23:59");
        deadline.markAsDone();
        deadline.unmarkAsDone();
        assertEquals("[D][ ] CS2103 Week 4 ip (by: Sep 7 2023 23:59)",
                deadline.toString());
    }
}
