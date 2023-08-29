package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_undone_test() {
        Deadline testObj = new Deadline("buy book", "July");
        assertEquals("[D][ ] buy book (by: July)", testObj.toString());
    }

    @Test
    public void fileFormat_undone_test() {
        Deadline testObj = new Deadline("buy book", "July");
        assertEquals("D|buy book|0|July", testObj.fileFormat());
    }

    @Test
    public void markAsDone_isDone_test() {
        Deadline testObj = new Deadline("buy book", "July");
        testObj.markAsDone();
        assertTrue(testObj.isDone);
    }

    @Test
    public void toString_emptyBy_test() {
        Deadline testObj = new Deadline("buy book", "");
        assertEquals("[D][ ] buy book (by: )", testObj.toString());
    }

}
