import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineStringConversion(){
        LocalDateTime deadlineDate = LocalDateTime.parse("2023-08-31 4PM",DateTimeFormatter.ofPattern("yyyy-MM-dd ha"));
        String deadlineTaskName = "return book";
        assertEquals("[D] [ ] return book (by: Aug 31 2023 4PM)", new Deadline(deadlineTaskName, deadlineDate).toString());
    }
}
