package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void toString_undone_Test(){
        Deadline testObj = new Deadline("buy book", "July");
        assertEquals("[D][ ] buy book (by: July)", testObj.toString());
    }

    @Test
    public void fileFormat_undone_Test(){
        Deadline testObj = new Deadline("buy book", "July");
        assertEquals("D|buy book|0|July", testObj.fileFormat());
    }

    @Test
    public void markAsDone_isDone_Test() {
        Deadline testObj = new Deadline("buy book", "July");
        testObj.markAsDone();
        assertTrue(testObj.isDone);
    }

    @Test
    public void toString_EmptyBy_Test() {
        Deadline testObj = new Deadline("buy book", "");
        assertEquals("[D][ ] buy book (by: )", testObj.toString());
    }

}
