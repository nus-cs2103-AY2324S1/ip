package bareum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventTaskTest {
    @Test
    public void makeEvent_stringCorrectDetails_success() {
        EventTask task = EventTask.makeEvent("project meeting", "Mon 2pm", "4pm");
        assertNotNull(task);
        assertEquals("project meeting", task.getDescription());
        assertEquals("Mon 2pm", task.getStartDateTime() );
        assertEquals("4pm", task.getEndDateTime());
    }


    @Test
    public void makeEvent_stringArrayCorrectInputs_success() {
        String[] taskInputs = {"E", "1", "project meeting", "Mon 2pm", "4pm"};
        EventTask task = EventTask.makeEvent(taskInputs);
        assertNotNull(task);
        assertEquals("project meeting", task.getDescription());
        assertEquals("Mon 2pm", task.getStartDateTime() );
        assertEquals("4pm", task.getEndDateTime());
    }
}
