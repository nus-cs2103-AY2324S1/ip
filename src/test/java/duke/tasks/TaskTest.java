package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testConstructorAndGetters() {
        Task task = new Task("Buy groceries", false, new ArrayList<>());
        assertEquals("Buy groceries", task.getDescription());
        assertFalse(task.getIsDone());

        Task completedTask = new Task("Finish assignment", true, new ArrayList<>());
        assertEquals("Finish assignment", completedTask.getDescription());
        assertTrue(completedTask.getIsDone());
    }

    @Test
    public void testSetDoneAndSetNotDone() {
        Task task = new Task("Read a book", false, new ArrayList<>());
        assertFalse(task.getIsDone());

        task.setDone();
        assertTrue(task.getIsDone());

        task.setNotDone();
        assertFalse(task.getIsDone());
    }

    @Test
    public void testStatusIcon() {
        Task undoneTask = new Task("Walk the dog", false, new ArrayList<>());
        assertEquals(" ", undoneTask.getStatusIcon());

        Task doneTask = new Task("Clean the room", true, new ArrayList<>());
        assertEquals("X", doneTask.getStatusIcon());
    }

    @Test
    public void testToString() {
        Task task = new Task("Finish report", false, new ArrayList<>());
        assertEquals("[ ] Finish report", task.toString());

        Task completedTask = new Task("Submit application", true, new ArrayList<>());
        assertEquals("[X] Submit application", completedTask.toString());
    }

    @Test
    public void testWrite() {
        Task task = new Task("Call a friend", false, new ArrayList<>());
        assertEquals("0 | Call a friend", task.write());

        Task completedTask = new Task("Go for a run", true, new ArrayList<>());
        assertEquals("1 | Go for a run", completedTask.write());
    }
}
