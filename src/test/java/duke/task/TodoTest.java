package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TodoTest {

    @Test
    public void testCreate() {
        Todo t = new Todo("Todo 1");
        assertEquals("Todo 1", t.message);
    }

    @Test
    public void testToSaveFormatString() {
        Todo t = new Todo("Todo 2");
        assertEquals("T | 0 | Todo 2", t.toSaveFormatString());
        t.markAsDone();
        assertEquals("T | 1 | Todo 2", t.toSaveFormatString());
    }

    @Test
    public void testToString() {
        Todo t = new Todo("Message");
        assertEquals("[T][ ] Message", t.toString());
        t.markAsDone();
        assertEquals("[T][X] Message", t.toString());
    }

    @Test
    public void testUpdate_validParams_success() {
        try {
            Todo t = new Todo("Message");
            t.update(UpdateType.DESCRIPTION, "New Todo");
            assertEquals("[T][ ] New Todo",
                    t.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUpdate_invalidUpdateDate1_dukeExceptionThrown() {
        try {
            Todo t = new Todo("Message");
            t.update(UpdateType.DESCRIPTION, "New Todo");
            t.update(UpdateType.DATE1, "2023-10-10T12:00:01");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Cannot update: Todos do not have dates!", e.getMessage());
        }
    }

    @Test
    public void testUpdate_invalidUpdateDate2_dukeExceptionThrown() {
        try {
            Todo t = new Todo("Message");
            t.update(UpdateType.DESCRIPTION, "New Todo");
            t.update(UpdateType.DATE2, "2023-10-10T12:00:01");
            fail("DukeException should be thrown!");
        } catch (DukeException e) {
            assertEquals("Cannot update: Todos do not have dates!", e.getMessage());
        }
    }

    @Test
    public void testClone() {
        try {
            Todo t1 = new Todo("Message");
            t1.markAsDone();
            Todo t2 = t1.clone();
            t2.update(UpdateType.DESCRIPTION, "New Message");

            assertEquals("[T][X] Message", t1.toString());
            assertEquals("[T][ ] New Message", t2.toString());
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }
}
