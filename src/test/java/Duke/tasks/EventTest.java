package Duke.tasks;

import core.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventCreation() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fromDateTime = LocalDateTime.parse("2023-09-01 05:00", formatter);
        LocalDateTime toDateTime = LocalDateTime.parse("2023-10-01 06:00", formatter);

        Events e = new Events("Table tennis", fromDateTime, toDateTime);
        assertEquals("Table tennis", e.getDescription());
    }
}
