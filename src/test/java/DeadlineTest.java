import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void deadlineStringConversion() {
        LocalDateTime deadlineDate = LocalDateTime.parse("2023-08-31 4PM",
                DateTimeFormatter.ofPattern("yyyy-MM-dd ha"));
        String deadlineTaskName = "return book";
        assertEquals("[D] [ ] return book (by: 2023-08-31 4 PM)",
                new Deadline(deadlineTaskName, deadlineDate).toString());
    }
}
