package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToStringUnmarkedEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime startTime = LocalDateTime.of(2023, 9, 30, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 9, 30, 11, 0);

        Event event = new Event("Meeting", false, startTime, endTime);
        String expected = "[E][ ] Meeting (from: Sep 30 2023 10:00 to: Sep 30 2023 11:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToStringMarkedEvent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 19, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 23, 0);

        Event event = new Event("Party", true, startTime, endTime);
        String expected = "[E][X] Party (from: Oct 01 2023 19:00 to: Oct 01 2023 23:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testGetStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime startTime = LocalDateTime.of(2023, 11, 15, 9, 0);

        Event event = new Event("Conference", false, startTime, LocalDateTime.now());
        assertEquals("Nov 15 2023 09:00", event.getStartTime().format(formatter));
    }

    @Test
    public void testSetStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime initialStartTime = LocalDateTime.of(2023, 11, 15, 9, 0);
        LocalDateTime updatedStartTime = LocalDateTime.of(2023, 11, 15, 10, 0);

        Event event = new Event("Conference", false, initialStartTime, LocalDateTime.now());
        event.setStartTime(updatedStartTime);
        assertEquals("Nov 15 2023 10:00", event.getStartTime().format(formatter));
    }

    @Test
    public void testGetEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime endTime = LocalDateTime.of(2023, 11, 16, 17, 0);

        Event event = new Event("Conference", false, LocalDateTime.now(), endTime);
        assertEquals("Nov 16 2023 17:00", event.getEndTime().format(formatter));
    }

    @Test
    public void testSetEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        LocalDateTime initialEndTime = LocalDateTime.of(2023, 11, 16, 17, 0);
        LocalDateTime updatedEndTime = LocalDateTime.of(2023, 11, 16, 18, 0);

        Event event = new Event("Conference", false, LocalDateTime.now(), initialEndTime);
        event.setEndTime(updatedEndTime);
        assertEquals("Nov 16 2023 18:00", event.getEndTime().format(formatter));
    }
}


