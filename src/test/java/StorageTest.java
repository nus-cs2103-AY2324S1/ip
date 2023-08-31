import duke.*;
import exceptions.ParseTaskFromStringException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void validToDo_parseTaskFromString() throws ParseTaskFromStringException {
        ToDo toDo = (ToDo) Storage.parseTaskFromString("T | 1 | eat");
        assertTrue(toDo.isDone());
        assertEquals("eat", toDo.getName());
    }

    @Test
    public void validDeadline_parseTaskFromString() throws ParseTaskFromStringException {
        Deadline deadline = (Deadline) Storage.parseTaskFromString("D | 0 | sleep | 2023-12-12 1410");
        assertFalse(deadline.isDone());
        assertEquals("sleep", deadline.getName());
        assertEquals(LocalDateTime.of(2023, 12, 12, 14, 10), deadline.getBy());
    }

    @Test
    public void validEvent_parseTaskFromString() throws ParseTaskFromStringException {
        Event event = (Event) Storage.parseTaskFromString("E | 0 | Mary's wedding | 2023-09-09 1800 | 2023-09-10 0000");
        assertFalse(event.isDone());
        assertEquals("Mary's wedding", event.getName());
        assertEquals(LocalDateTime.of(2023, 9, 9, 18, 0), event.getStart());
        assertEquals(LocalDateTime.of(2023, 9, 10, 0, 0), event.getEnd());
    }

    @Test
    public void missingTypeField_parseTaskFromString() {
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("0 | Prepare for interview");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("1 | Get parcel | 2023-12-12 1410");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("0 | Team meeting | 2023-09-09 1800 | 2023-09-10 0000");
        });
    }

    @Test
    public void missingStatusField_parseTaskFromString() {
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("T | Prepare for interview");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("D | Get parcel | 2023-12-12 1410");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("E | Team meeting | 2023-09-09 1800 | 2023-09-10 0000");
        });
    }

    @Test
    public void missingNameFields_parseTaskFromString() {
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("T | 0");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("D | 0 | 2023-12-12 1410");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("E | 0 | 2023-09-09 1800 | 2023-09-10 0000");
        });
    }

    @Test
    public void missingDeadlineFields_by_parseTaskFromString() {
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("D | 0 | eat");
        });
    }

    @Test
    public void missingEventFields_startEnd_parseTaskFromString() {
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("E | 0 | eat");
        });
        assertThrows(ParseTaskFromStringException.class, () -> {
            Storage.parseTaskFromString("E | 0 | Gym session | 2023-09-10 0000");
        });
    }
}
