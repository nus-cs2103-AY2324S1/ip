package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void toString_undone_test() {
        Event testObj = new Event("buy book", "July", "August");
        assertEquals("[E][ ] buy book (from: July to: August)", testObj.toString());
    }

    @Test
    public void fileFormat_undone_test() {
        Event testObj = new Event("buy book", "July", "August");
        assertEquals("E|buy book|0|July|August", testObj.fileFormat());
    }

    @Test
    public void markAsDone_isDone_test() {
        Event testObj = new Event("buy book", "July", "August");
        testObj.markAsDone();
        assertTrue(testObj.isDone);
    }

    @Test
    public void toString_emptyFromTo_test() {
        Event testObj = new Event("buy book", "", "");
        assertEquals("[E][ ] buy book (from:  to: )", testObj.toString());
    }
}
