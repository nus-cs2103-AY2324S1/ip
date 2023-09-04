package duke.tasks;

import duke.exceptions.InvalidStartEndException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testStartEndException1() throws InvalidStartEndException {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.plusHours(9);
        InvalidStartEndException ex = assertThrows(InvalidStartEndException.class,
                () -> new Event(0, "bake cookies", start, end));
        assertEquals("(・´з`・) Uh oh...start must be after end!", ex.getMessage());
    }

    @Test
    public void testStartEndException2() throws InvalidStartEndException {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.plusMinutes(1);
        InvalidStartEndException ex = assertThrows(InvalidStartEndException.class,
                () -> new Event(0, "bake cookies", start, end));
        assertEquals("(・´з`・) Uh oh...start must be after end!", ex.getMessage());
    }

    @Test
    public void testConvertTask1() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(0, "bake cookies", start, end);
        String expected = "E | 0 | bake cookies | " + start.format(formatter) +
                " | " + end.format(formatter);
        assertEquals(expected, event.convertTask());
    }

    @Test
    public void testConvertTask2() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(1, "bake cookies", start, end);
        String expected = "E | 1 | bake cookies | " + start.format(formatter) +
                " | " + end.format(formatter);
        assertEquals(expected, event.convertTask());
    }

    @Test
    public void testToString1() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(0, "bake cookies", start, end);
        String expected = "[E] [ ] bake cookies (from: " + start.format(formatter) +
                " to: " + end.format(formatter) + ")";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToString2() throws InvalidStartEndException {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(9);
        Event event = new Event(1, "bake cookies", start, end);
        String expected = "[E] [X] bake cookies (from: " + start.format(formatter) +
                " to: " + end.format(formatter) + ")";
        assertEquals(expected, event.toString());
    }
}
