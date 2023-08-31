package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline test = new Deadline("deadline homework /by 04/07/2003 2359");
        assertEquals("[D] [ ] homework | DUE: 04 July 2003, 11:59 PM |", test.toString());
    }

    @Test
    public void toFileString() {
        Deadline test = new Deadline("deadline homework /by 04/07/2003 2359");
        assertEquals("D | 0 | homework | 04 July 2003, 11:59 PM", test.toFileString());
    }
}
