package taskclasses;

import taskclasses.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class DeadlineTest {
    @Test
    public void testFormatToFile() {
        LocalDate dueDate = LocalDate.of(2023, 9, 1);
        Deadline deadline = new Deadline("Complete project", dueDate);

        String expected = "D | 0 | Complete project | Sep 1 2023";
        assertEquals(expected, deadline.formatToFile());
    }

    @Test
    public void testToString() {
        LocalDate dueDate = LocalDate.of(2023, 9, 1);
        Deadline deadline = new Deadline("Submit report", true, dueDate);

        String formattedDueDate = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String expected = "[D][X] Submit report (by: " + formattedDueDate + ")";
        assertEquals(expected, deadline.toString());
    }
}
