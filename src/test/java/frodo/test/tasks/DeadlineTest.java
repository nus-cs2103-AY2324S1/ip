package frodo.test.tasks;

import core.DukeException;
import org.junit.jupiter.api.Test;
import frodo.tasks.Deadlines;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {
    @Test
    public void createDeadLine_WithValidInputs_ReturnsCorrectDescription() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadlineTime = LocalDateTime.parse("2023-09-01 05:00", formatter);
        Deadlines deadline = new Deadlines("Sample Deadline", deadlineTime);

        assertEquals("Sample Deadline", deadline.getDescription());
    }

    @Test
    public void createDeadLine_WithValidInputs_ReturnsCorrectMarkStatus() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadlineTime = LocalDateTime.parse("2023-09-01 05:00", formatter);
        Deadlines deadline = new Deadlines("Sample Deadline", deadlineTime);

        assertFalse(deadline.isCompleted(), "New todo should not be marked as done");
    }
}
