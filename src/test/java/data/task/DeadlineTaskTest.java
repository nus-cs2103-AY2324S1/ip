package data.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class DeadlineTaskTest {
    @Test
    public void testStringConversion() {
        DeadlineTask testTask = new DeadlineTask("test", "01/01/2023 1200");
        assertEquals("[D][ ] test (by: 01 Jan 2023 12:00 PM)", testTask.toString());
        testTask.setDone();
        assertEquals("[D][X] test (by: 01 Jan 2023 12:00 PM)", testTask.toString());
    }

    @Test
    public void getType_emptyInput_correctString() {
        assertEquals("Deadline", new DeadlineTask("test", "01/01/2023 1200").getType());
    }

    @Test
    public void getDateTime_emptyInput_correctString() {
        assertEquals("01/01/2023 1200",
                new DeadlineTask("test", "01/01/2023 1200").getDateTime());
    }
}