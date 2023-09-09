package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    private LocalDateTime date = LocalDateTime.of(2023, 9, 3, 17, 00);

    /**
     * Tests the localDateToString method
     */
    @Test
    public void testLocalDateToString() {
        assertEquals("Sep 3 2023 5:00PM", Deadline.localDatetoString(date));
    }

    /**
     * Tests the toString method for a Deadline object
     */
    @Test
    public void toString_deadline_success() throws DukeException {
        assertEquals("[D][ ] return book (by: Sep 10 2023 5:00PM)",
                Deadline.newDeadline("return book ", "Sun 1700").toString());
    }

    /**
     * Tests the toFileString method for a Deadline object
     */
    @Test
    public void toFileString_deadline_success() throws DukeException {
        assertEquals("D | O | return book | Sep 10 2023 5:00PM",
                Deadline.newDeadline("return book ", "Sun 1700").toFileString());
    }

}
