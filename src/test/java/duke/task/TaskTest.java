package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
class TaskTest {


    private Task task = new Task("Test Task");
    @Test
    public void testGetDescription_success() {
        assertEquals("Test Task", task.getDescription());
    }

    @Test
    public void testMarkDone_success() {
        assertEquals("Nice! I've marked this duke.task as done:\n[X] Test Task", task.markDone());
    }

    @Test
    public void testMarkUndone_success() {
        assertEquals("OK, I've marked this duke.task as not done yet:\n[ ] Test Task", task.markUndone());
    }

    @Test public void testToString() {
        assertEquals("[ ] Test Task", task.toString());
        task.markDone();
        assertEquals("[X] Test Task", task.toString());
    }

    @Test
    public void testToString_success() {
        assertEquals("[ ] Test Task", task.toString());
    }

    @Test
    public void testGetStatusIcon_success() {
        assertEquals(" ", task.getStatusIcon());
    }
}
