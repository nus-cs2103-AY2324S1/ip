package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {

    // Testing the creation of an Event task and its default values.
    @Test
    public void createEventTest() {
        Event event = new Event("Team Meeting", "9:00AM", "11:00AM");

        assertEquals(TaskType.EVENT, event.getTaskType());
        assertEquals("Team Meeting", event.getDescription());
        assertFalse(event.isDone()); // Ensure that by default, tasks are not done.
        assertEquals("9:00AM", event.getFrom());
        assertEquals("11:00AM", event.getTo());
    }

    // Testing the string representation of an Event task.
    @Test
    public void toStringTest() {
        Event event = new Event("Team Meeting", "9:00AM", "11:00AM");
        assertEquals("[E][ ] Team Meeting (from: 9:00AM to: 11:00AM)", event.toString());
    }

    // Testing the transformFormat method for an Event task.
    @Test
    public void transformFormatTest() {
        Event event = new Event("Team Meeting", "9:00AM", "11:00AM");
        assertEquals("E | false | Team Meeting | 9:00AM | 11:00AM", event.transformFormat());
    }
}

