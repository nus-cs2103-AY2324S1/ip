package friday;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


/**
 * Represents a test suite for the Deadline class in the Friday application.
 */
public class DeadlineTest {

    /**
     * Tests that valid date formats are correctly parsed and represented in the Deadline class.
     */
    @Test
    public void testValidDateFormats() {
        // Test the first valid pattern "M/d/yyyy"
        Deadline deadline1 = new Deadline("Test Task", "1/12/2019");
        assert "[D][ ] Test Task (by: Jan 12 2019)".equals(deadline1.toString())
                : "Mismatch in expected output for date format M/d/yyyy";

        // Test the second valid pattern "MM-dd-yyyy"
        Deadline deadline2 = new Deadline("Test Task", "01-12-2019");
        assert "[D][ ] Test Task (by: Jan 12 2019)".equals(deadline2.toString())
                : "Mismatch in expected output for date format MM-dd-yyyy";

        // Test the third valid pattern "yyyy/MM/dd"
        Deadline deadline3 = new Deadline("Test Task", "2019/01/12");
        assert "[D][ ] Test Task (by: Jan 12 2019)".equals(deadline3.toString())
                : "Mismatch in expected output for date format yyyy/MM/dd";
    }

    /**
     * Tests that an invalid date format throws the expected DateTimeParseException with a relevant error message.
     */
    @Test
    public void testInvalidDateFormat() {
        Deadline deadline = new Deadline("Test Task", "12/2019");
        try {
            deadline.toString();
            assert false : "Expected DateTimeParseException but no exception was thrown.";
        } catch (DateTimeParseException e) {
            assert e.getMessage().contains("Unsupported date format: 12/2019")
                    : "Unexpected error message: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        DeadlineTest test = new DeadlineTest();
        test.testValidDateFormats();
        test.testInvalidDateFormat();
    }
}
