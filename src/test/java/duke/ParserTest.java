package duke;

import duke.Deadline;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
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