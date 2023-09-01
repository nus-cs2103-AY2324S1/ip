package sisyphus.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDate deadlineDate = LocalDate.of(2023, 9, 15); // Change this to a valid date
        Deadline deadline = new Deadline("Finish project", deadlineDate);
        String expected = "[D][ ] Finish project (by: Sep 15 2023)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToSaveFormat() {
        LocalDate deadlineDate = LocalDate.of(2023, 9, 15); // Change this to a valid date
        Deadline deadline = new Deadline("Finish project", false, deadlineDate);
        String expected = "D,Finish project,0,2023-09-15";
        assertEquals(expected, deadline.toSaveFormat());
    }

    @Test
    public void testIsDone() {
        LocalDate deadlineDate = LocalDate.of(2023, 9, 15); // Change this to a valid date
        Deadline deadline = new Deadline("Finish project", true, deadlineDate);
        assertEquals(true, deadline.isDone);
    }
}
