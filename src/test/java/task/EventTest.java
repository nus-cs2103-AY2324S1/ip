package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    @Test
    public void toString_undone_Test(){
        Event testObj = new Event("buy book", "July", "August");
        assertEquals("[E][ ] buy book (from: July to: August)", testObj.toString());
    }

    @Test
    public void fileFormat_undone_Test(){
        Event testObj = new Event("buy book", "July", "August");
        assertEquals("E|buy book|0|July|August", testObj.fileFormat());
    }

    @Test
    public void markAsDone_isDone_Test() {
        Event testObj = new Event("buy book", "July", "August");
        testObj.markAsDone();
        assertTrue(testObj.isDone);
    }

    @Test
    public void toString_EmptyFromTo_Test() {
        Event testObj = new Event("buy book", "", "");
        assertEquals("[E][ ] buy book (from:  to: )", testObj.toString());
    }
}
