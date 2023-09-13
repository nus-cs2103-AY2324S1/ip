package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test the functionality of the Deadline class.
 */
public class DeadlineTest {

    /**
     * Test the getFormattedDeadline method of the Deadline class.
     * It checks if the formatted deadline matches the expected result.
     */
    @Test
    public void testGetFormattedDeadline_taskWithDeadline_formattedDateTime() {
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
        // Create a LocalDateTime object for the deadline
        LocalDate deadline = LocalDate.of(2023, 8, 29);

        // Create a Task object with the deadline
        Deadline task = new Deadline("Test Deadline", deadline);

        // Get the formatted deadline
        String formattedDeadline = task.getDeadline().format(DATE_FORMATTER);

        // Format the expected result using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String expectedFormattedDeadline = deadline.format(formatter);

        // Check if the formatted deadlines match
        assertEquals(expectedFormattedDeadline, formattedDeadline);
    }
}