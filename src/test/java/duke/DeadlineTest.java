package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    private LocalDateTime date = LocalDateTime.of(2023, 9, 3, 17, 00);

    @Test
    public void testLocalDateToString() {
        assertEquals("Sep 3 2023 5:00PM", Deadline.localDatetoString(date));
    }
    @Test
    public void toString_validTimeInput_success() throws DukeException {
        assertEquals("[D][ ] return book (by: Sep 24 2023 5:00PM)",
                Deadline.newDeadline("return book ", "Sun 1700").toString());
    }
    @Test
    public void toString_invalidTimeInput_exceptionThrown() {
        try {
            Deadline.newDeadline("return book ", "Su 1700");
        } catch (DukeException e) {
            assertEquals("Only inputs such as 'Mon', 'Tuesday', 'sat' allowed!", e.getMessage());
        }
    }
    @Test
    public void toFileString_validTimeInput_success() throws DukeException {
        assertEquals("D | O | return book | Sep 24 2023 5:00PM",
                Deadline.newDeadline("return book ", "Sun 1700").toFileString());
    }
    @Test
    public void convertToLocalDateTime_validTimeInput_success() throws DukeException {
        LocalDateTime temp = Deadline.convertToLocalDateTime("3/9/2023 1700");
        assertEquals(date, temp);
    }
    @Test
    public void convertToLocalDateTime_invalidTimeInput_exceptionThrown() {
        try {
            Deadline.convertToLocalDateTime("3/9/2023");
        } catch (DukeException e) {
            assertEquals("put in a time pls", e.getMessage());
        }
    }
    @Test
    public void parseDateTime_invalidTimeInput_exceptionThrown() {
        try {
            Deadline.newDeadline("return book ", "Sun");
        } catch (DukeException e) {
            assertEquals("Put in a valid time input!", e.getMessage());
        }
    }
}
