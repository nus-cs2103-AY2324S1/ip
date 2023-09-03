package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_validInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Event event = new Event("Meeting", dateTime);

        assertEquals("Meeting", event.getDescription());
        assertFalse(event.isDone());
        assertEquals(dateTime, event.getDateTime());
    }

    @Test
    public void constructor_invalidDateTime_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Event("Meeting", LocalDateTime.parse("2023-08-30 14:30:00"));
        });
    }

    @Test
    public void toFileString_isNotDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Event event = new Event("Meeting", dateTime);

        String expected = "E | 0 | Meeting | 2023-08-30 14:30";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void toFileString_isDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Event event = new Event("Meeting", dateTime);

        event.markAsDone();
        String expected = "E | 1 | Meeting | 2023-08-30 14:30";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void toString_isNotDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Event event = new Event("Meeting", dateTime);

        String expected = "[E][ ] Meeting (at: 30 Aug 2023 2:30pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toString_isDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Event event = new Event("Meeting", dateTime);
        event.markAsDone();

        String expected = "[E][X] Meeting (at: 30 Aug 2023 2:30pm)";
        assertEquals(expected, event.toString());
    }
}

