package didier.task;

import didier.exception.FileCorruptedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void parseFileString_validMarkedToDo_success() {
        try {
            assertEquals(Task.parseFileString("T|1|borrow book").toString(), "[T][X] borrow book");
        } catch (FileCorruptedException e) {
            fail();
        }
    }
    @Test
    public void parseFileString_validUnmarkedDeadline_success() {
        try {
            assertEquals(Task.parseFileString("D|0|borrow book|2011-10-11").toString(),
                    "[D][ ] borrow book (by: Oct 11 2011)");
        } catch (FileCorruptedException e) {
            fail();
        }
    }

    @Test
    public void parseFileString_validUnmarkedEvent_success() {
        try {
            assertEquals(Task.parseFileString("E|0|borrow book|2011-10-11|2011-11-12").toString(),
                    "[E][ ] borrow book (from: Oct 11 2011 to: Nov 12 2011)");
        } catch (FileCorruptedException e) {
            fail();
        }
    }
    @Test
    public void parseFileString_invalidTask_exceptionThrown() {
        try {
            assertEquals(Task.parseFileString("X|0|borrow book|2011-12-11"),null);
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("The task file was corrupted. Please delete the file.", e.getMessage());
        }
    }

    @Test
    public void parseFileString_invalidDate_exceptionThrown() {
        try {
            assertEquals(Task.parseFileString("D|0|borrow book|2011-13-11"),null);
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("The task file was corrupted. Please delete the file.", e.getMessage());
        }
    }

    @Test
    public void parseFileString_invalidSeparator_exceptionThrown() {
        try {
            assertEquals(Task.parseFileString("D,0,borrow book,2011-10-11"),null);
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("The task file was corrupted. Please delete the file.", e.getMessage());
        }
    }

    @Test
    public void parseFileString_truncatedEvent_exceptionThrown() {
        try {
            assertEquals(Task.parseFileString("E|0|borrow book|2011-10-11"),null);
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("The task file was corrupted. Please delete the file.", e.getMessage());
        }
    }


}
