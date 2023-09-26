package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineTest1() {
        LocalDateTime deadlineTime = LocalDateTime.parse("2023-09-12 14:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        try {
            Deadline deadlineTask = new Deadline("cs2103 assignment", deadlineTime);
            assertEquals(deadlineTask.getTaskAsString(), "[D][ ] cs2103 assignment (by: Sep 12 2023 14:00)");
        } catch (Exception e) {
            fail();
        }
    }

}
