package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidStartEndException;

public class EventTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void event_exceptionThrown_hours() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.plusHours(9);
        InvalidStartEndException ex = assertThrows(
                InvalidStartEndException.class, () -> new Event(0, "bake cookies", start, end));
        assertEquals("(・´з`・) Uh oh... start must be after end!", ex.getMessage());
    }

    @Test
    public void event_exceptionThrown_minutes() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.plusMinutes(1);
        InvalidStartEndException ex = assertThrows(
                InvalidStartEndException.class, () -> new Event(0, "bake cookies", start, end));
        assertEquals("(・´з`・) Uh oh... start must be after end!", ex.getMessage());
    }

    @Test
    public void convertTask_uncompleted() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(0, "bake cookies", start, end);
        String expected = "E | 0 | bake cookies | " + start.format(formatter)
                + " | " + end.format(formatter);
        assertEquals(expected, event.convertTask());
    }

    @Test
    public void convertTask_completed() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(1, "bake cookies", start, end);
        String expected = "E | 1 | bake cookies | " + start.format(formatter)
                + " | " + end.format(formatter);
        assertEquals(expected, event.convertTask());
    }

    @Test
    public void toString_uncompleted() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(0, "bake cookies", start, end);
        String expected = "[E] [ ] bake cookies (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toString_completed() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(1, "bake cookies", start, end);
        String expected = "[E] [X] bake cookies (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")";
        assertEquals(expected, event.toString());
    }
}
