package friday;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.format.DateTimeParseException;

/**
 * Represents a test suite for the Deadline class in the Friday application.
 */
public class DeadlineTest {

    /**
     * Tests that valid date formats are correctly parsed and represented in the Deadline class.
     */
    @Test
    public void testValidDateFormats() {
        Deadline deadline1 = new Deadline("Test Task", "1/12/2019");
        assertEquals("[D][ ] Test Task (by: Jan 12 2019)", deadline1.toString());
    }

    /**
     * Tests that an invalid date format throws the expected DateTimeParseException with a relevant error message.
     */
    @Test
    public void testInvalidDateFormat() {
        Deadline deadline = new Deadline("Test Task", "12/2019");
        try {
            deadline.toString();
        } catch (DateTimeParseException e) {
            assert e.getMessage().contains("Unsupported date format: 12/2019");
        }
    }
}
