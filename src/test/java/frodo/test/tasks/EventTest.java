package frodo.test.tasks;

import core.DukeException;
import frodo.tasks.Events;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    /**
     * Tests the creation of an Event.
     * Ensures that when an event is created with a specific description,
     * the getDescription method returns the correct description.
     *
     * @throws DukeException if there is an error during event creation.
     */
    @Test
    public void createEvent_WithValidDates_ReturnsCorrectDescription() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse("2023-09-01 05:00", formatter);
        LocalDateTime toDateTime = LocalDateTime.parse("2023-10-01 06:00", formatter);

        Events e = new Events("Table tennis", fromDateTime, toDateTime);
        assertEquals("Table tennis", e.getDescription());
    }

    @Test
    public void createEvent_WithEmptyDescription_ThrowsException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse("2023-09-01 05:00", formatter);
        LocalDateTime toDateTime = LocalDateTime.parse("2023-10-01 06:00", formatter);

        assertThrows(DukeException.class, () -> {
            new Events("", fromDateTime, toDateTime);
        });
    }

    @Test
    public void createEvent_WithStartDateLaterThanEndDate_ThrowsException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse("2024-09-01 05:00", formatter);
        LocalDateTime toDateTime = LocalDateTime.parse("2023-10-01 06:00", formatter);

        assertThrows(DukeException.class, () -> {
            new Events("", fromDateTime, toDateTime);
        });
    }
}
