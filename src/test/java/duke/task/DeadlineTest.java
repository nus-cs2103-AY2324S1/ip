package duke.task;

import duke.task.Deadline;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineStringConversion(){
        assertEquals(
                "[D][ ] Run (by: Nov 01 2023)",
                new Deadline("Run", LocalDate.parse("2023-11-01")).toString()
        );

        assertEquals(
                "[D][ ] Project (by: Oct 23 2023)",
                new Deadline("Project", LocalDate.parse("2023-10-23")).toString()
        );
    }
}
