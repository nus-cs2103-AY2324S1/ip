package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        Task actual = new Event("project meeting", "6/9/2023 1523", "6/9/2023 1623", false,
                null);
        assertEquals("E | 0 | project meeting | 06 Sep 2023 3:23PM - 06 Sep 2023 4:23PM", actual.toString());

        Task actualWithReminder = new Event("project meeting", "6/9/2023 1523", "6/9/2023 1623", false,
                "3/9/2023 1523");
        assertEquals("E | 0 | project meeting | 06 Sep 2023 3:23PM - 06 Sep 2023 4:23PM"
                + " | Reminder: 03 Sep 2023 3:23PM", actualWithReminder.toString());
    }
}
