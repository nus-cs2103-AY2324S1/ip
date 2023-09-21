package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testToStringUnmarkedEvent() {
        Event event = new Event("Meeting", false, "2023-09-30 10:00 AM", "2023-09-30 11:00 AM");
        String expected = "[E][ ] Meeting (from: 2023-09-30 10:00 AM to: 2023-09-30 11:00 AM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToStringMarkedEvent() {
        Event event = new Event("Party", true, "2023-10-01 7:00 PM", "2023-10-01 11:00 PM");
        String expected = "[E][X] Party (from: 2023-10-01 7:00 PM to: 2023-10-01 11:00 PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testGetStartTime() {
        Event event = new Event("Conference", false, "2023-11-15 9:00 AM", "2023-11-16 5:00 PM");
        assertEquals("2023-11-15 9:00 AM", event.getStartTime());
    }

    @Test
    public void testSetStartTime() {
        Event event = new Event("Conference", false, "2023-11-15 9:00 AM", "2023-11-16 5:00 PM");
        event.setStartTime("2023-11-15 10:00 AM");
        assertEquals("2023-11-15 10:00 AM", event.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        Event event = new Event("Conference", false, "2023-11-15 9:00 AM", "2023-11-16 5:00 PM");
        assertEquals("2023-11-16 5:00 PM", event.getEndTime());
    }

    @Test
    public void testSetEndTime() {
        Event event = new Event("Conference", false, "2023-11-15 9:00 AM", "2023-11-16 5:00 PM");
        event.setEndTime("2023-11-16 6:00 PM");
        assertEquals("2023-11-16 6:00 PM", event.getEndTime());
    }
}

