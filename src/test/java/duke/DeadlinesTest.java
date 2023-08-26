package duke;

import org.junit.jupiter.api.Test;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlinesTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void stringRepTest() {
        Deadline task = new Deadline("Testname1", "Tuesday");
        assertEquals("[D][ ] Testname1 (by: Tuesday)", task.toString());
    }

    @Test
    public void stringRepTest_DateTime() {
        Deadline task = new Deadline("Testname1", "2/12/2019 1800");
        assertEquals("[D][ ] Testname1 (by: 2 Dec 2019)", task.toString());
    }
}
