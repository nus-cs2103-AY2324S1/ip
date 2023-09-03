package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_validInput_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Deadline deadline = new Deadline("Finish project", dateTime);
        assertEquals("Finish project", deadline.getDescription());
        assertFalse(deadline.isDone());
        assertEquals(dateTime, deadline.getDateTime());
    }

    @Test
    public void constructor_invalidDateTime_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Event("Finish Project", LocalDateTime.parse("2023-08-30 14:30:00"));
        });
    }

    @Test
    public void toFileString_isNotDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Deadline deadline = new Deadline("Finish project", dateTime);

        String expected = "D | 0 | Finish project | 2023-08-30 14:30";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void toFileString_isDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Deadline deadline = new Deadline("Finish project", dateTime);

        deadline.markAsDone();
        String expected = "D | 1 | Finish project | 2023-08-30 14:30";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void toString_isNotDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Deadline deadline = new Deadline("Finish project", dateTime);

        String expected = "[D][ ] Finish project (by: 30 Aug 2023 2:30pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_isDone_success() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 30, 14, 30);
        Deadline deadline = new Deadline("Finish project", dateTime);

        deadline.markAsDone();
        String expected = "[D][X] Finish project (by: 30 Aug 2023 2:30pm)";
        assertEquals(expected, deadline.toString());
    }

}

