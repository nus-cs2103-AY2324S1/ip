package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("task", "11/11/2002 11:11", "11/11/2002 22:22", false);
    @Test
    public void testMarkDeadline() {
        assertEquals(true, event.markTask(), "Marking event should work");
    }

    @Test
    public void testUnmarkTodo() {
        assertEquals(false, event.markTask(), "Unmarking event should work");
    }

    @Test
    public void testToString() {
        assertEquals("[E][ ] task (from: 11/11/2002 11:11, to: 11/11/2002 22:22)", event.toString(), "event.toString() should work");
    }
}
