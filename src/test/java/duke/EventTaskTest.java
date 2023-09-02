package duke;

import org.junit.jupiter.api.Test;

import duke.task.EventTask;

public class EventTaskTest {
    @Test
    public void testToString() {
        EventTask eventTask = new EventTask("project meeting", "2020-08-25 14:00", "2020-08-25 16:00");
        assert eventTask.toString().equals("[E][ ] project meeting (from: 2020-08-25 14:00 to: 2020-08-25 16:00)");
    }

    @Test
    public void testMarkTaskAsDone() {
        EventTask eventTask = new EventTask("project meeting", "2020-08-25 14:00", "2020-08-25 16:00");
        eventTask.markAsDone();
        assert eventTask.toString().equals("[E][X] project meeting (from: 2020-08-25 14:00 to: 2020-08-25 16:00)");
    }
}
