package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    void testToString() throws DukeException {
        Deadline deadline = new Deadline("Finish assignment", false, "23/09/2023 1300");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String expectedFormattedDateTime = LocalDateTime.parse("23/09/2023 1300",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")).format(outputFormatter);
        String expectedToString = "[D][ ] Finish assignment (by: " + expectedFormattedDateTime + ")";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    void testGenerateStr() throws DukeException {
        Deadline deadline = new Deadline("Finish assignment", false, "23/09/2023 1300");
        String expectedGenerateStr = "D | 0 | Finish assignment | 23/09/2023 1300";
        assertEquals(expectedGenerateStr, deadline.generateStr());
    }
}
